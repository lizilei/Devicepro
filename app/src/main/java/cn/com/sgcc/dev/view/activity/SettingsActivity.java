package cn.com.sgcc.dev.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import cn.com.sgcc.dev.BaseApplication;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.FileRestoreAdapter;
import cn.com.sgcc.dev.adapter.MyHeadMainRecyclerAdapters;
import cn.com.sgcc.dev.adapter.SettingsAdapter;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.constants.JiaoYanDataConstant;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.recylerUtil.MyItemClickListener;
import cn.com.sgcc.dev.customeView.recylerUtil.SwipeItemLayout;
import cn.com.sgcc.dev.dao2.DaoMaster;
import cn.com.sgcc.dev.dbUtils.DBManager;
import cn.com.sgcc.dev.model2.print.Print;
import cn.com.sgcc.dev.model2.regist.DecryptKey;
import cn.com.sgcc.dev.model2.vo.DataHolder;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.DesUtils;
import cn.com.sgcc.dev.utils.FileUtils;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.ScreenUtils;
import cn.com.sgcc.dev.utils.ToastUtils;

/**
 * <p>@description:</p>
 *
 * @author lxf
 * @version 1.0.0
 */

public class SettingsActivity extends BaseActivity {
    private CustomDialog dialog_setting;
    private TextView app_toolbar_center;
    private ImageView app_toolbar_left, app_toolbar_sao1;
    private RecyclerView recyclerView_setting;
    private SettingsAdapter adapter;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        initEvevt();

    }

    private void initView() {
        recyclerView_setting = (RecyclerView) findViewById(R.id.recyclerView_setting);
        app_toolbar_center = (TextView) findViewById(R.id.app_toolbar_center);
        app_toolbar_left = (ImageView) findViewById(R.id.app_toolbar_left);
        app_toolbar_sao1 = (ImageView) findViewById(R.id.app_toolbar_sao1);
        app_toolbar_center.setText("设置");
        stringList.add("系统设置");
        stringList.add("校验设置");
        stringList.add("执行文件");
        stringList.add("更新key文件");
        stringList.add("打印设置");
        stringList.add("批量打印");
        stringList.add("检查更新");
        stringList.add("关于");

        adapter = new SettingsAdapter(this);
        adapter.setDatas(stringList);
        recyclerView_setting.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_setting.setAdapter(adapter);
        recyclerView_setting.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        IntentFilter filters = new IntentFilter("cn.sgg.finishActivity");
        registerReceiver(receivers, filters);

    }

    BroadcastReceiver receivers = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            SettingsActivity.this.finish();
        }
    };

    private void initEvevt() {
        adapter.setOnItemClickListener(
                new MyItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String user = PreferenceUtils.getPrefString(SettingsActivity.this, "userInfo", null);
                        if (user.split("-")[0].equals(Constants.admin)) {
                            if (stringList.get(position).equals("系统设置")) {
                            } else {
                                ToastUtils.showLongToast(SettingsActivity.this, "系统管理员无此操作！");
                                return;
                            }
                        }

                        if (stringList.get(position).equals("系统设置")) {
                            changesetting();
                        } else if (stringList.get(position).equals("校验设置")) {
                            startActivity(new Intent(SettingsActivity.this, JiaoYanActivity.class));
                        } else if (stringList.get(position).equals("打印设置")) {
                            Intent intent1 = new Intent();
                            intent1.setClassName("an.qt.android", "an.qt.android.MainActivity");
                            Print printBean1 = new Print();
                            printBean1.setForm_type("FORM_PRINTER_SET");
                            intent1.putExtra("json", new Gson().toJson(printBean1));
                            try {
                                startActivity(intent1);
                            } catch (Exception e) {
                                e.printStackTrace();
                                ToastUtils.showToast(SettingsActivity.this, "未安装打印机应用,请先安装");
                            }
                        } else if (stringList.get(position).equals("批量打印")) {
                            Intent intent = new Intent();
                            intent.setClassName("an.qt.android", "an.qt.android.MainActivity");
                            Print printBean = new Print();
                            printBean.setForm_type("FORM_CUSTOM_PRINT");
                            intent.putExtra("json", new Gson().toJson(printBean));
                            try {
                                startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                                ToastUtils.showToast(SettingsActivity.this, "未安装打印机应用,请先安装");
                            }
                        } else if (stringList.get(position).equals("检查更新")) {
                            File updateFile = getUpdateFile(Constants.APP_UPDATE);
                            if (updateFile != null) {
                                String name = updateFile.getName();
                                //校验APK名称
                                String[] s1 = name.split("\\.");
                                if (s1[0].length() >= 7) {
                                    if (s1.length == 3 && !s1[1].equals("") && isNumeric(s1[1]) && s1[0].substring(0, 6).equals("RLST_V")
                                            && isNumeric(s1[0].substring(6, 7)) && s1[2].equals("apk")) {
                                        int start = name.indexOf(".") - 1;
                                        int last = name.lastIndexOf(".");

                                        float version = Float.parseFloat(name.substring(start, last));
                                        float currentVersion = Float.parseFloat(AppUtils.getVersion(SettingsActivity.this));
                                        if (version > currentVersion) {
                                            Intent intent = new Intent(Intent.ACTION_VIEW);
                                            String type = "application/vnd.android.package-archive";
                                            intent.setDataAndType(Uri.fromFile(updateFile), type);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        } else {
                                            ToastUtils.showLongToast(SettingsActivity.this, "您当前已是最新版本，无需更新...");
                                        }
                                    } else {
                                        ToastUtils.showLongToast(SettingsActivity.this, "APK文件不合法,请检查");
                                    }
                                } else {
                                    ToastUtils.showLongToast(SettingsActivity.this, "APK文件不合法,请检查");
                                }
                            } else {
                                ToastUtils.showLongToast(SettingsActivity.this, "未检测到更新文件，请检查是否有更新文件...");
                            }

                        } else if (stringList.get(position).equals("关于")) {
                            startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
                        } else if (stringList.get(position).equals("执行文件")) {
                            doExecute();
                        } else if (stringList.get(position).equals("更新key文件")) {
                            if (AppUtils.isRegist()) {
                                Intent intent = new Intent(SettingsActivity.this, ChooseFileActivity.class);
                                intent.putExtra("chooseKey", "");
                                startActivityForResult(intent, ChooseFileActivity.RESULTCODE);
                            } else {
                                ToastUtils.showLongToast(SettingsActivity.this, "试用模式下不支持此功能！");
                            }
                        }
                    }
                }
        );


        //返回按钮
        app_toolbar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsActivity.this.finish();
            }
        });

        //结束按钮X
        app_toolbar_sao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, MainActivitys.class));
                sendBroadcast(new Intent("cn.sgg.finishActivity"));
                SettingsActivity.this.finish();
            }
        });
    }

    //获取更新文件
    private File getUpdateFile(String path) {
        File[] files = new File(path).listFiles();
        if (files.length > 0) {
            return files[0];
        } else {
            return null;
        }
    }

    //判断是否为数字
    public static boolean isNumeric(String str) {
        String reg = "[0-9]*";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str).matches();
    }

    //执行文件内容
    private void doExecute() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, Constants.INPUT_PATH + Constants.DB_NAME, null);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        db.setLocale(Locale.CHINESE);
        String sql = String.format(AppUtils.execute(false));
//        List<String> sqlList = new ArrayList<>();
//        PreferenceUtils.setPrefString(this,"EXECUTE_SQL","");
        if (sql == null || sql.equalsIgnoreCase("") || sql.equalsIgnoreCase("null")) {
            ToastUtils.showToast(this, "文件读取失败，请确认");
            db.close();
            return;
        }
//        if (sql.equalsIgnoreCase(PreferenceUtils.getPrefString(this,"EXECUTE_SQL","")+"")){
//            ToastUtils.showToast(this, "操作重复，文件已经执行");
//            db.close();
//            return;
//        }
        PreferenceUtils.setPrefString(this, "EXECUTE_SQL", sql + "");
        String[] ss = sql.split(";");
        if (sql != null && !sql.equals("") && !sql.equalsIgnoreCase("null")) {
            try {
                //执行文件
                for (int i = 0; i < ss.length; i++) {
                    db.execSQL("" + ss[i]);
                    Log.e("getSQL : ", ss[i]);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        db.close();
        AppUtils.execute(true);
        ToastUtils.showToast(this, "文件执行完毕");
    }

    //修改默认设置
    private void changesetting() {
        if (dialog_setting != null && dialog_setting.isShowing()) {
            return;
        } else {
            View view = LayoutInflater.from(this).inflate(R.layout.activity_setting, null);
            dialog_setting = new CustomDialog(this, R.style.dialog_alert_style, 0);
            final EditText tcsj = (EditText) view.findViewById(R.id.et_setting_tcsj);
            final EditText sycs = (EditText) view.findViewById(R.id.et_setting_sycs);
            final EditText et_url = (EditText) view.findViewById(R.id.et_setting_url);
            final CheckBox cb_jgms = (CheckBox) view.findViewById(R.id.cb_jgms_true);
            final CheckBox cb_tbms = (CheckBox) view.findViewById(R.id.cb_tbms_true);

            final LinearLayout ll_sycs = (LinearLayout) view.findViewById(R.id.ll_sycs);
            final LinearLayout ll_tcsj = (LinearLayout) view.findViewById(R.id.ll_tcsj);

            //默认不出现系统管理员权限,出现不同显示
            String uName = PreferenceUtils.getPrefString(this, "userInfo", null);
            if (uName != null && !uName.equals(Constants.admin + "-" + Constants.admin_pw)) {
                ll_sycs.setVisibility(View.GONE);
                ll_tcsj.setVisibility(View.GONE);
            }

            Map<String, String> keyMap = new HashMap<>();
            final Map<String, String> txtMap = AppUtils.setTxt(keyMap, "", false);
            String symax = txtMap.get("symax") + "";
            String csmax = txtMap.get("csmax") + "";
            String jgms = txtMap.get("jgms") + "";
            String tbms = txtMap.get("tbms") + "";
            int time = Constants.ch_times / 60000;
            Constants.sy_cout = Integer.parseInt(symax);
            tcsj.setText(time + "");
            sycs.setText(Constants.sy_cout + "");
            et_url.setText(txtMap.get("base_url"));
            if (jgms.equals("是")) {
                cb_jgms.setChecked(true);
            }
            if (tbms.equals("是")) {
                cb_tbms.setChecked(true);
            }

            Button okay = (Button) view.findViewById(R.id.queding);
            Button cancel = (Button) view.findViewById(R.id.quxiao);

            //确定
            okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int times = Constants.ch_times / 60000;
                    int count = Constants.sy_cout;
                    if (ll_sycs.getVisibility() == View.VISIBLE) {
                        times = Integer.parseInt(tcsj.getText() + "");
                        count = Integer.parseInt(sycs.getText() + "");
                    }
//                    (Constants.ch_times/60000+"");

                    String baseUrl = et_url.getText().toString() + "";
                    if (times < 30) {
                        ToastUtils.showToast(SettingsActivity.this, "超时时间不小于30分钟");
                    } else if (count < 5) {
                        ToastUtils.showToast(SettingsActivity.this, "试用次数不小于5次");
                    } else {
                        Constants.ch_times = times * 60000;
                        Constants.sy_cout = count;
                        txtMap.put("symax", count + "");
                        txtMap.put("csmax", Constants.ch_times + "");
                        if (!baseUrl.equals("") && baseUrl.startsWith("http://") ||
                                !baseUrl.equals("") && baseUrl.startsWith("https://")) {
                            if (AppUtils.isContainChinese(baseUrl) || AppUtils.isContainZM(baseUrl.split("//")[1])) {
                                ToastUtils.showToast(SettingsActivity.this, "无效的url地址！");
                                return;
                            } else {
                                txtMap.put("base_url", baseUrl);
                            }
                        } else {
                            ToastUtils.showToast(SettingsActivity.this, "无效的url地址！");
                            return;
                        }
                        if (cb_jgms.isChecked()) {
                            txtMap.put("jgms", "是");
                        } else {
                            txtMap.put("jgms", "否");
                        }
                        if (cb_tbms.isChecked()) {
                            txtMap.put("tbms", "是");
                        } else {
                            txtMap.put("tbms", "否");
                        }
                        //放开生成码使用
//                        txtMap.put("syms","否");
//                        txtMap.put("bdms","否");
                        Map<String, String> keyMap = AppUtils.setTxt(txtMap, "", true);

                        dialog_setting.dismiss();
                        ToastUtils.showToast(SettingsActivity.this, "修改成功");
                    }
                }
            });
            //取消
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_setting.dismiss();
                }
            });

            dialog_setting.setContentView(view, new ViewGroup.LayoutParams(ScreenUtils.getScreenWidth(this) * 3 / 4,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog_setting.setCanceledOnTouchOutside(false);
            dialog_setting.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ChooseFileActivity.RESULTCODE) {
            String choosePath = data.getStringArrayListExtra(ChooseFileActivity.SELECTPATH).get(0);
            //调用解密的方法
            String decryptKey;
            try {
                DesUtils des = new DesUtils("tongjifenxjiamichuli");
                decryptKey = des.decrypt(FileUtils.readAppKey(choosePath));
            } catch (Exception e) {
                ToastUtils.showLongToast(this, "key文件不合法请重新选择！");
                return;
            }
            DecryptKey dy = new Gson().fromJson(decryptKey, DecryptKey.class);
            if (dy != null) {
                Map<String, String> txtMaps = AppUtils.setTxt(new HashMap<>(), "", false);
                String[] sbxx = txtMaps.get("sbxx").split("-");
                if (!dy.getANDROID_ID().equals(sbxx[2]) || !dy.getIMEI().equals(sbxx[1]) ||
                        !dy.getSERIALNUMBER().equals(sbxx[3]) || !dy.getZCM().equals(sbxx[4])) {
                    //key文件内容与设备信息不符，注册失败
                    ToastUtils.showLongToast(SettingsActivity.this, "无效的KEY文件,请重新选择！");
                    return;
                }

                BaseApplication.getInstance().setDecryptKey(dy);
                //将key文件复制到工程目录下
                FileUtils.copyFile(choosePath, Constants.APP_DEFAULT);

                //清空保存的变电站信息
                PreferenceUtils.setPrefString(SettingsActivity.this, "czmc", null);
                PreferenceUtils.setPrefString(SettingsActivity.this, "czmcID", null);
                DataHolder.getInstance().init();
                BaseApplication.getInstance().setDecryptKey(null);

                //验校跳转
                PreferenceUtils.setPrefBoolean(this, "qy_sy", false);
                ToastUtils.showLongToast(SettingsActivity.this, "KEY文件更新成功,请重新登录！");
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                //关闭自动备份
                unBind();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receivers);
    }
}
