package cn.com.sgcc.dev.view.activity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sgcc.dev.BaseApplication;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.RLST_USER;
import cn.com.sgcc.dev.model2.UserInfo;
import cn.com.sgcc.dev.model2.regist.ResultValueBean;
import cn.com.sgcc.dev.model2.regist.UserBean;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.net.BaseCallModel3;
import cn.com.sgcc.dev.net.MyCallBack3;
import cn.com.sgcc.dev.net.ProjectAPIService;
import cn.com.sgcc.dev.net.RetrofitUtils;
import cn.com.sgcc.dev.utils.AesUtil;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.CheckPerssionUtil;
import cn.com.sgcc.dev.utils.FileUtils;
import cn.com.sgcc.dev.utils.MyMD5Util;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.ScreenUtils;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.viewinterface.PermissionGrant;
import retrofit2.Response;


/**
 * <p>@description:</p>
 *
 * @author lxf
 * @version 1.0.0
 * @since 2018/1/12
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, PermissionGrant {
    private TextInputEditText etUser;
    private TextInputEditText etPass;
    private Button btnLogin;
    private CheckBox cb_remember;
    private TextView tv_set;
    private LoadingDialog progressDialog;
    private CustomDialog dialog_setting;

    private BroadcastReceiver installedReceiver;

    public static final int REQPERMISSION = 100;

    private List<CZCS> orglist = new ArrayList<>();//原始数据

    private boolean flag;
    public static LoginActivity instance = null;
    Map<String, String> txtMap;
    String syms;
    String sycs;
    String symax;
    String csmax;
    String bdms;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            switch (msg.what) {
                case 0:
                    ToastUtils.showToast(LoginActivity.this, "操作成功！");
                    progressDialog.setTitle("登陆中，请稍候！");
                    progressDialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            checkHasDBFile(etUser.getText().toString(), etPass.getText().toString());
                        }
                    }).start();
                    break;
                case 1:
                    progressDialog.setTitle("登陆中，请稍候！");
                    progressDialog.show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            checkHasDBFile(etUser.getText().toString(), etPass.getText().toString());
                        }
                    }).start();
                    break;
                case 2:
                    ToastUtils.showToast(LoginActivity.this, "操作成功！");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.title_login));
        }
        initView();

        //检测版本权限
        checkPermissions(REQPERMISSION, Constants.PERMISSIONS);
    }

    /**
     * 初始化
     */
    public void init() {
        startTimer_for();

        instance = this;

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_ADDED"); //安装
        filter.addAction("android.intent.action.PACKAGE_REMOVED"); //卸载
        filter.addDataScheme("package");
        this.registerReceiver(installedReceiver, filter);

        Map<String, String> keyMap = new HashMap<>();
        txtMap = AppUtils.setTxt(keyMap, "", false);
        syms = txtMap.get("syms") + "";
        sycs = txtMap.get("sycs") + "";
        symax = txtMap.get("symax") + "";
        csmax = txtMap.get("csmax") + "";
        bdms = txtMap.get("bdms") + "";
        if (bdms.equals("是")) {

        } else {
            //设备唯一性校验放开
            startActivity(new Intent(LoginActivity.this, CheckActivity.class));
        }

        if (PreferenceUtils.getPrefBoolean(this, "isChecked", false)) {
            String userInfo = PreferenceUtils.getPrefString(this, "userInfo", null);
            if (userInfo != null) {
                etUser.setText(userInfo.split("-")[0]);
                //etPass.setText(userInfo.split("-")[1]);  //取消保存密码
                cb_remember.setChecked(true);
            }
        }

        //生成校验原始数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppUtils.createJYFile(LoginActivity.this);
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Constants.loginout) {
            etUser.setText("");
            etPass.setText("");
            cb_remember.setChecked(false);
        }
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (!AppUtils.checkNetwork(this)) {
//            if (!flag) {
//                flag = true;
//                //检测sg下是否存在db文件，存在则提示用户是否覆盖
//                if (FileUtils.hasZip()) {//存在
//                    WindowManager.LayoutParams lp = getWindow().getAttributes();
//                    lp.alpha = 0.5f;
//                    getWindow().setAttributes(lp);
//                    showDialog(false);
//                }
//            }
//        }
//    }

    /**
     * 统一弹窗
     * boolean isLogin
     */
    private void showDialog(final boolean isLogin) {
        final PopupWindow pop = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.common_dialog_view, null);
        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tv_ensure = (TextView) view.findViewById(R.id.tv_ensure);
        tv_message.setText("检测到本地目录下存在新的数据文件，是否要覆盖");

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //取消
                if (isLogin) {
                    handler.sendEmptyMessage(1);
                }
                pop.dismiss();
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.setTitle("正在操作，请稍候！");
                progressDialog.show();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String show = FileUtils.unZip();
                        if (show != null && !show.equals("")) {
                            File[] allFiles = new File(Constants.INPUT_PATH).listFiles();
                            for (File f : allFiles) {
                                String name = f.getName();
                                if (name.substring(name.length() - 2, name.length()).equals("db")) {
                                    Constants.DB_NAME = name;
                                    break;
                                }
                            }

                            if (isLogin) {
                                handler.sendEmptyMessage(0);
                            } else {
                                handler.sendEmptyMessage(2);
                            }
                        } else {
                            LoginActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                }
                            });
                            ToastUtils.showLongToast(LoginActivity.this, show);
                        }

                    }
                });
                thread.start();
            }
        });

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setOutsideTouchable(false);
        pop.setContentView(view);
        pop.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    private void initView() {
        etUser = (TextInputEditText) findViewById(R.id.et_username);
        etPass = (TextInputEditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tv_set = (TextView) findViewById(R.id.tv_set);
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);
        progressDialog = new LoadingDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        btnLogin.setOnClickListener(this);
        cb_remember.setOnClickListener(this);
        tv_set.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                final String username = etUser.getText().toString();
                final String pwd = etPass.getText().toString();
                if (username.equals("")) {
                    ToastUtils.showToast(this, "请输入用户名...");
                    return;
                } else if (username.length() > 40) {
                    ToastUtils.showToast(this, "用户名不能超过40个字符...");
                    return;
                }
                if (pwd.equals("")) {
                    ToastUtils.showToast(this, "请输入密码...");
                    return;
                }
                Map<String, String> keyMap = new HashMap<>();
                txtMap = AppUtils.setTxt(keyMap, "", false);
                syms = txtMap.get("syms") + "";
                sycs = txtMap.get("sycs") + "";
                symax = txtMap.get("symax") + "";
                csmax = txtMap.get("csmax") + "";
                bdms = txtMap.get("bdms") + "";
                int cs = Integer.parseInt(sycs);
                int max = Integer.parseInt(symax);

                if (txtMap.get("syms").equals("是") && cs >= max) {
                    ToastUtils.showToast(this, "试用次数达到上限，无法继续试用");
                    //验校跳转CheckActivity
                    startActivity(new Intent(this, CheckActivity.class));
                    return;
                }
                //检测sg下是否存在db文件，存在则提示用户是否覆盖
                if (FileUtils.hasZip()) {//存在
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 0.5f;
                    getWindow().setAttributes(lp);
                    showDialog(true);
                } else {
                    progressDialog.setTitle("登陆中，请稍候！");
                    progressDialog.show();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            checkHasDBFile(username, pwd);
                        }
                    }).start();
                }
                break;
            case R.id.tv_set:
                changesetting();
                break;
        }
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
            final LinearLayout ll_jgms = (LinearLayout) view.findViewById(R.id.ll_jgms);
            final LinearLayout ll_tbms = (LinearLayout) view.findViewById(R.id.ll_tbms);

            //默认不出现系统管理员权限
            ll_sycs.setVisibility(View.GONE);
            ll_tcsj.setVisibility(View.GONE);
            ll_jgms.setVisibility(View.GONE);
            ll_tbms.setVisibility(View.GONE);

            Map<String, String> keyMap = new HashMap<>();
            final Map<String, String> txtMap = AppUtils.setTxt(keyMap, "", false);
            String symax = txtMap.get("symax") + "";
            String csmax = txtMap.get("csmax") + "";
            String jgms = txtMap.get("jgms") + "";
            String tbms = txtMap.get("tbms") + "";
            et_url.setText(txtMap.get("base_url"));
//            if (jgms.equals("是")) {
//                cb_jgms.setChecked(true);
//            }
     /*       if (tbms.equals("是")) {
                cb_tbms.setChecked(true);
            }*/

            Button okay = (Button) view.findViewById(R.id.queding);
            Button cancel = (Button) view.findViewById(R.id.quxiao);

            //确定
            okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String baseUrl = et_url.getText().toString() + "";
                    if (!baseUrl.equals("") && baseUrl.startsWith("http://") ||
                            !baseUrl.equals("") && baseUrl.startsWith("https://")) {
                        if (AppUtils.isContainChinese(baseUrl) || AppUtils.isContainZM(baseUrl.split("//")[1])) {
                            ToastUtils.showToast(LoginActivity.this, "无效的url地址！");
                            return;
                        } else {
                            txtMap.put("base_url", baseUrl);
                        }
                    } else {
                        ToastUtils.showToast(LoginActivity.this, "无效的url地址！");
                        return;
                    }

                    Map<String, String> keyMap = AppUtils.setTxt(txtMap, "", true);

                    String[] ss = baseUrl.replace("http://", "").split(":");

                    writeNsData(ss[0], ss[1]);

                    dialog_setting.dismiss();
                    ToastUtils.showToast(LoginActivity.this, "修改成功");
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

    /**
     * 检测是否有db文件,有则登录
     */
    public void checkHasDBFile(String username, String pwd) {
        if (username.equals(Constants.admin) && pwd.equals(Constants.admin_pw)) {
            String uName = PreferenceUtils.getPrefString(this, "userInfo", null);
            if (uName != null && !uName.equals(username + "-" + pwd)) {
                PreferenceUtils.setPrefString(this, "czmc", null);
                PreferenceUtils.setPrefString(this, "czmcID", null);
            }
            PreferenceUtils.setPrefString(this, "userInfo", username + "-" + pwd);
            if (cb_remember.isChecked()) {
                PreferenceUtils.setPrefBoolean(this, "isChecked", true);
            } else {
                PreferenceUtils.setPrefBoolean(this, "isChecked", false);
            }

            Map<String, String> keyMap = new HashMap<>();
            txtMap = AppUtils.setTxt(keyMap, "", false);
            syms = txtMap.get("syms") + "";
            sycs = txtMap.get("sycs") + "";
            symax = txtMap.get("symax") + "";
            csmax = txtMap.get("csmax") + "";
            bdms = txtMap.get("bdms") + "";
            ToastUtils.showToast(this, "系统管理员身份登录");
            progressDialog.dismiss();
            Intent intent = new Intent(this, MainActivitys.class);
            intent.putExtra("admin", "");
            startActivity(new Intent(intent));
            return;
        }

        boolean isOnline;
        if (AppUtils.checkNetwork(this)) {
            isOnline = true;
        } else {
            isOnline = false;
        }

        if (isOnline) { //在线
            doLoginOnLine(username, pwd);
        } else { //离线
            doLogin(username, pwd);
        }
    }

    /**
     * 写入南思ip和端口
     *
     * @param ip
     * @param port
     */
    public void writeNsData(String ip, String port) {
        File file = new File(Constants.NSClient_PATH + "ServerAddress.json");
        if (!file.exists()) {
            try {
                file.createNewFile();

                JSONObject obj = new JSONObject();
                obj.put("ip", ip);
                obj.put("port", port);

                FileUtils.writeString(obj.toString(), file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONObject obj = new JSONObject(FileUtils.readFileContent(file));
                String obj_ip = obj.getString("ip");
                String obj_port = obj.getString("port");

                if (!ip.equals(obj_ip) || !port.equals(obj_port)) {
                    JSONObject object = new JSONObject();
                    object.put("ip", ip);
                    object.put("port", port);

                    FileUtils.writeString(object.toString(), file);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在线登录的方法
     *
     * @param username
     * @param pwd
     */
    private void doLoginOnLine(final String username, final String pwd) {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("userName", username);
        map.put("password", AesUtil.encryptPassword(pwd));

        map1.put("params", map);

        Map<String, String> data = AppUtils.setTxt(map, "", false);
        if (data.containsKey("base_url")) {
            Constants.ROOT_URL = data.get("base_url");

            String[] ss = Constants.ROOT_URL.replace("http://", "").split(":");

            writeNsData(ss[0], ss[1]);
        }

        ProjectAPIService apiService = RetrofitUtils.retrofit.create(ProjectAPIService.class);

        apiService.doLoginISC(Constants.ROOT_URL + Constants.GET_USER_LOGIN, map1).enqueue(new MyCallBack3<BaseCallModel3<ResultValueBean>>() {

            @Override
            public void onSuc(Response<BaseCallModel3<ResultValueBean>> response) {
                if (response.isSuccessful() && response.raw().code() == 200) {
                    Constants.Cookie = response.headers().get("Set-Cookie");

                    PreferenceUtils.setPrefString(LoginActivity.this, "userInfo", username + "-" + pwd);
                    PreferenceUtils.setPrefBoolean(LoginActivity.this, "isChecked", cb_remember.isChecked());

                    txtMap = AppUtils.setTxt(new HashMap<>(), "", false);
                    syms = txtMap.get("syms") + "";
                    sycs = txtMap.get("sycs") + "";
                    symax = txtMap.get("symax") + "";
                    csmax = txtMap.get("csmax") + "";
                    bdms = txtMap.get("bdms") + "";
                    int cs = Integer.parseInt(sycs);
                    int max = Integer.parseInt(symax);
                    if (syms.equals("是")) {
                        if (cs < max) {
                            //设备唯一性校验放开
                            cs = cs + 1;
                            PreferenceUtils.setPrefInt(LoginActivity.this, "sycs", cs);
                            txtMap.put("sycs", cs + "");
                            AppUtils.setTxt(txtMap, "", true);

                        }
                    }

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    progressDialog.dismiss();
                    Constants.isLoginOnLine = true;
                } else {
                    ToastUtils.showLongToast(LoginActivity.this, "登录异常，请重试...");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFail(String msg) {
                progressDialog.dismiss();
                if (msg.equals("运行异常啦！")) {
                    ToastUtils.showLongToast(LoginActivity.this, "用户名或密码错误！");
                }
            }

            @Override
            public void onAutoLogin() {

            }
        });
    }

    /**
     * 离线登录的方法
     *
     * @param username
     * @param pwd
     */
    public void doLogin(String username, String pwd) {
        File[] allFiles = new File(Constants.INPUT_PATH).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return FileUtils.getExtensionName(name).equals("db");
            }
        });

        if (allFiles.length > 0) { //存在离线数据包
            Constants.DB_NAME = allFiles[0].getName();

            Map<String, String> map = new HashMap<>();
            map.put("user.userName", username);
            map.put("user.password", pwd);

            Map<String, String> data = AppUtils.setTxt(map, "", false);
            if (data.containsKey("base_url")) {
                Constants.ROOT_URL = data.get("base_url");
            }

            IDaoUtil util = new DaoUtil(this);

            UserInfo info = util.getUserInfo(username, pwd);
            if (info == null) {
                RLST_USER user = util.getUserByName(username);
                if (user == null) {
                    ToastUtils.showToast(this, "用户名或密码不正确...");
                    progressDialog.dismiss();
                    return;
                } else {
                    String s = MyMD5Util.digest(pwd) + "";
                    if (!s.equals(user.getPassword())) {
                        ToastUtils.showToast(this, "用户名或密码不正确...");
                        progressDialog.dismiss();
                        return;
                    }
                    UserBean bean = new UserBean(user.getUserName(), "", user.getSsgs());
                    PreferenceUtils.setPrefString(this, "Info", new Gson().toJson(bean));
                }
            } else {
                UserBean bean = new UserBean(info.getNameInTf(), "", info.getSsgs());
                PreferenceUtils.setPrefString(this, "Info", new Gson().toJson(bean));
            }
            String uName = PreferenceUtils.getPrefString(this, "userInfo", null);
            if (uName != null && !uName.equals(username + "-" + pwd)) {
                PreferenceUtils.setPrefString(this, "czmc", null);
                PreferenceUtils.setPrefString(this, "czmcID", null);
            }

            PreferenceUtils.setPrefString(this, "userInfo", username + "-" + pwd);

            PreferenceUtils.setPrefBoolean(this, "isChecked", cb_remember.isChecked());

            txtMap = AppUtils.setTxt(new HashMap<>(), "", false);
            syms = txtMap.get("syms") + "";
            sycs = txtMap.get("sycs") + "";
            symax = txtMap.get("symax") + "";
            csmax = txtMap.get("csmax") + "";
            bdms = txtMap.get("bdms") + "";
            int cs = Integer.parseInt(sycs);
            int max = Integer.parseInt(symax);
            if (syms.equals("是")) {
                if (cs < max) {
                    //设备唯一性校验放开
                    cs = cs + 1;
                    PreferenceUtils.setPrefInt(LoginActivity.this, "sycs", cs);
                    txtMap.put("sycs", cs + "");
                    AppUtils.setTxt(txtMap, "", true);
                }
            }
            //超时重置
            Constants.loginout = false;
            //清空保存的变电站信息
            PreferenceUtils.setPrefString(LoginActivity.this, "czmc", null);
            PreferenceUtils.setPrefString(LoginActivity.this, "czmcID", null);

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            Constants.isLoginOnLine = false;
            progressDialog.dismiss();
            finish();
        } else {
            ToastUtils.showToast(this, "不存在本地数据文件，请导入...");
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //判断是否是登录超时
        if (Constants.loginout) {
//            ToastUtils.showToast(this,"操作超时，请重新登录，，，，，");
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        ActivityManager activityManager = (ActivityManager) LoginActivity.this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
        for (ActivityManager.AppTask appTask : appTaskList) {
            appTask.finishAndRemoveTask();
        }
        // 4. 结束进程
        System.exit(0);
    }

    @Override
    protected void onDestroy() {
        if (installedReceiver != null) {
            unregisterReceiver(installedReceiver);
        }
        super.onDestroy();
    }

    /**
     * 检查权限，有则直接执行后续操作，无则走请求权限流程
     */
    public void checkPermissions(int requestCode, @NonNull String... permissions) {
        if (CheckPerssionUtil.getInstance(this).hasPermissions(permissions)) {
            onPermissionGranted(requestCode);
        } else {
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
    }

    @Override
    public void onPermissionGranted(int requestCode) {
        if (requestCode == REQPERMISSION) {
            BaseApplication.getInstance().initFile();

            init();
        }
    }

    @Override
    public void onPermissionDenied() {
        CheckPerssionUtil.getInstance(this).deniedEverDialog(deniedEverMessage()
                , deniedEverPositive(), deniedEverNegative());
    }

    @Override
    public void onPermissionDeniedOnce() {
        ToastUtils.showLongToast(this, "必要权限已被关闭，请打开。");
    }

    /**
     * 永久拒绝提示语句
     */
    public String deniedEverMessage() {
        return "必要权限已被关闭，请前往应用信息页面开启权限。";
    }

    /**
     * 永久拒绝跳转文字
     */
    public String deniedEverPositive() {
        return "去开启";
    }

    /**
     * 永久拒绝取消文字
     */
    public String deniedEverNegative() {
        return "取消";
    }
}