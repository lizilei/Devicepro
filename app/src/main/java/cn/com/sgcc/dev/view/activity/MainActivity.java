package cn.com.sgcc.dev.view.activity;//package cn.com.sgcc.dev.view.activity;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.NonNull;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.widget.Toolbar;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.zxing.activity.CaptureActivity;
//
//import java.io.File;
//import java.io.FileFilter;
//import java.io.Serializable;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.regex.Pattern;
//
//import cn.com.sgcc.dev.R;
//import cn.com.sgcc.dev.constants.Constants;
//import cn.com.sgcc.dev.customeView.CustomDialog;
//import cn.com.sgcc.dev.customeView.LoadingDialog;
//import cn.com.sgcc.dev.dbUtils.DBManager;
//import cn.com.sgcc.dev.dbUtils.DaoUtil;
//import cn.com.sgcc.dev.dbUtils.IDaoUtil;
//import cn.com.sgcc.dev.model2.BHPZ;
//import cn.com.sgcc.dev.model2.BHXHRJBB;
//import cn.com.sgcc.dev.model2.BKXX;
//import cn.com.sgcc.dev.model2.FZBHSB;
//import cn.com.sgcc.dev.model2.ICDXX;
//import cn.com.sgcc.dev.model2.LTYSBXH;
//import cn.com.sgcc.dev.utils.AppUtils;
//import cn.com.sgcc.dev.utils.OutputDBAsyncTask;
//import cn.com.sgcc.dev.utils.PreferenceUtils;
//import cn.com.sgcc.dev.utils.TimeUtil;
//import cn.com.sgcc.dev.utils.ToastUtils;
//import cn.com.sgcc.dev.view.fragment.DeviceListFragment;
//import cn.com.sgcc.dev.view.fragment.DeviceListFragments;
//
///**
// * <p>@description:</p>
// *
// * @author lizilei
// * @version 1.0.0
// * @Email lizilei_warms@163.com
// * @since 2017/8/16
// */
//
//public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
//
//    private TextView app_toolbar_center, addTZXX;
//    private TextView app_toolbar_right;
//    private DrawerLayout drawer;
//    private IDaoUtil util;
//    private LoadingDialog dialog;
//
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 1) {
//                dialog.dismiss();
//                ToastUtils.showToast(MainActivity.this, "导出成功！");
//                sendBroadcast(new Intent("cn.sgg.fzbhsb"));
//            }
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        dialog = new LoadingDialog(this);
//        dialog.setTitle("正在导出数据文件，请稍候！");
//        util = new DaoUtil(this);
//        initView();
////        initFragment();
//    }
//
////    private void initFragment() {
////        getSupportFragmentManager()
////                .beginTransaction()
////                .replace(R.id.content_main, new DeviceListFragment())
////                .commit();
////    }
//
//    @Override
//    public void onBackPressed() {
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    private void initView() {
//        app_toolbar_center = (TextView) findViewById(R.id.app_toolbar_center);
//        app_toolbar_center.setText(PreferenceUtils.getPrefString(this, "czmc", null));
//        app_toolbar_right = (TextView) findViewById(R.id.app_toolbar_sao);
//        app_toolbar_right.setVisibility(View.VISIBLE);
//        app_toolbar_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 100);
////                List<BKXX> bkxxList = DBManager.getmInstance(MainActivity.this).getSession().getBKXXDao().loadAll();
////                List<ICDXX> icdxxList = DBManager.getmInstance(MainActivity.this).getSession().getICDXXDao().loadAll();
//            }
//        });
//
//        //新增设备******************************
//        addTZXX = (TextView) findViewById(R.id.addTZXX);
//        addTZXX.setVisibility(View.VISIBLE);
//        addTZXX.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startActivityForResult(new Intent(MainActivity.this, DeviceDetailsActivity.class), 100);
//                showTypeDialog();
//            }
//        });
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setTitle("");
//        setSupportActionBar(toolbar);
//
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        View v = navigationView.getHeaderView(0);
//        TextView t1 = (TextView) v.findViewById(R.id.header_name);
//        TextView t2 = (TextView) v.findViewById(R.id.header_time);
//
//        String name = PreferenceUtils.getPrefString(this, "userInfo", null);
//        if (name != null)
//            t1.setText(name.split("-")[0]);
//        t2.setText(TimeUtil.getCurrentTime());
//    }
//
//    public CustomDialog type_dialog;
//    private List data;
//    String sbsbdm;
//
//    public void showTypeDialog() {
//        if (type_dialog != null && type_dialog.isShowing()) {
//            return;
//        } else {
//            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_one_device_dialog_item, null);
//            type_dialog = new CustomDialog(MainActivity.this, R.style.dialog_alert_style, 0);
//            // 根据id在布局中找到控件对象
//            TextView tv_dialog_protect = (TextView) view.findViewById(R.id.fragment_device_details_dialog_protect);
//            TextView tv_dialog_auxiliary = (TextView) view.findViewById(R.id.fragment_device_details__dialog_auxiliary);
//            data = new ArrayList();
//
//            //保护设备
//            tv_dialog_protect.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //ToastUtils.showToast(MainActivity.this, "当前选择保护设备");
//                    Intent intent = new Intent(MainActivity.this, DeviceDetailsActivity.class);
//                    intent.putExtra("ZZTYPE", "BHSB");
//                    intent.putExtra("STATE", "C");
//                    startActivity(intent);
//                    type_dialog.dismiss();
//                }
//            });
//            //辅助设备
//            tv_dialog_auxiliary.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //ToastUtils.showToast(MainActivity.this, "当前选择辅助设备");
//                    Intent intent = new Intent(MainActivity.this, DeviceDetailsActivity.class);
//                    intent.putExtra("ZZTYPE", "FZSB");
//                    intent.putExtra("STATE", "C");
//                    startActivity(intent);
//                    type_dialog.dismiss();
//
//                }
//            });
//            type_dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT));
//            type_dialog.setCanceledOnTouchOutside(true);
//            type_dialog.show();
//        }
//    }
//
//    //判断是否为数字
//    public static boolean isNumeric(String str) {
//        Pattern pattern = Pattern.compile("[0-9]*");
//        return pattern.matcher(str).matches();
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
////            case R.id.nav_camera: //导入
////                break;
//            case R.id.nav_gallery://导出
//                dialog.setCanceledOnTouchOutside(false);
//                dialog.setCancelable(false);
//                dialog.show();
//                new OutputDBAsyncTask(this, mHandler).execute();
//                break;
//            case R.id.nav_slideshow://更换厂站
//                finish();
//                break;
//            case R.id.nav_manage: //关于
//                startActivity(new Intent(this, AboutActivity.class));
//                break;
//            case R.id.nav_share://更新  app-debug2.0.apk   RLST_V0.1.apk
//                File updateFile = getUpdateFile(Constants.APP_UPDATE);
//                if (updateFile != null) {
//                    String name = updateFile.getName();
//                    //校验APK名称
//                    String[] s1 = name.split("\\.");
//                    if (s1[0].length() >= 7) {
//                        if (s1.length == 3 && !s1[1].equals("") && isNumeric(s1[1]) && s1[0].substring(0, 6).equals("RLST_V")
//                                && isNumeric(s1[0].substring(6, 7)) && s1[2].equals("apk")) {
//                            int start = name.indexOf(".") - 1;
//                            int last = name.lastIndexOf(".");
//
//                            float version = Float.parseFloat(name.substring(start, last));
//                            float currentVersion = Float.parseFloat(AppUtils.getVersion(this));
//                            if (version > currentVersion) {
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                String type = "application/vnd.android.package-archive";
//                                intent.setDataAndType(Uri.fromFile(updateFile), type);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                            } else {
//                                ToastUtils.showLongToast(this, "您当前已是最新版本，无需更新...");
//                            }
//                        } else {
//                            ToastUtils.showLongToast(this, "APK文件不合法,请检查");
//                        }
//                    } else {
//                        ToastUtils.showLongToast(this, "APK文件不合法,请检查");
//                    }
//                } else {
//                    ToastUtils.showLongToast(this, "未检测到更新文件，请检查是否有更新文件...");
//                }
//                break;
//            case R.id.nav_send: //退出登录
//                startActivity(new Intent(this, LoginActivity.class));
//                finish();
//                break;
//        }
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    private File getUpdateFile(String path) {
//        File[] files = new File(path).listFiles();
//        if (files.length > 0) {
//            return files[0];
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100 && resultCode == RESULT_OK) {
//            Intent intent = new Intent();
//            String sbsbdm = data.getExtras().getString("result").trim();
//            BHPZ bhpz = util.getBHPZBySbsbdm(sbsbdm);
//            if (bhpz != null) {
//                intent.setClass(this, DeviceDetailsActivity.class);
//                intent.putExtra("ZZTYPE", "BHSB");
//                intent.putExtra("BHSB", bhpz);
//                intent.putExtra("STATE", "M");
//            } else {
//                FZBHSB fzbhsb = (FZBHSB) util.getFZSBBySbsbdm(sbsbdm, "", "");
//                if (fzbhsb != null) {
//                    intent.setClass(this, DeviceDetailsActivity.class);
//                    intent.putExtra("ZZTYPE", "FZSB");
//                    intent.putExtra("FZSB", fzbhsb);
//                    intent.putExtra("STATE", "M");
//                } else {//去查出厂信息库
//                    Object o = util.getObjectFromCCXX(sbsbdm);
//                    if (o == null) {//未关联到出厂库，跳没有找到的页面
//                        intent.setClass(this, SaoMaFailedActivity.class);
//                        intent.putExtra("sbsbdm", sbsbdm);
//                    } else {//关联到出厂库
//                        List<BKXX> bkxxList = util.getCCXXBK(sbsbdm);
//                        List<Object> rjbbList;
//                        ICDXX icdxx = util.getICDXXFromCCXX(sbsbdm);
//                        intent.putExtra("sbsbdm", sbsbdm);
//                        if (icdxx != null)
//                            intent.putExtra("ICDXX", icdxx);
//                        if (o instanceof BHPZ) {
//                            boolean isSix = ((BHPZ) o).getSfltysb().equals("是");
//                            boolean is2013 = ((BHPZ) o).getLtybzbb().equals("2013版");
//                            rjbbList = util.getCCXXRJBB(isSix, is2013, sbsbdm);
//
//                            intent.setClass(this, DeviceDetailsActivity.class);
//                            intent.putExtra("ZZTYPE", "BHSB");
//                            intent.putExtra("BHSB", (BHPZ) o);
//                            if (bkxxList != null && bkxxList.size() > 0)
//                                intent.putExtra("BKXX", (Serializable) bkxxList);
//                            if (rjbbList != null && rjbbList.size() > 0)
//                                intent.putExtra("BHXHRJBB", (Serializable) rjbbList);
//                            intent.putExtra("saoma", "");
//                            intent.putExtra("STATE", "C");
//                        } else if (o instanceof FZBHSB) {
//                            boolean isSix = ((FZBHSB) o).getSfltysb().equals("是");
//                            boolean is2013 = ((FZBHSB) o).getLtybzbb().equals("2013版");
//                            rjbbList = util.getCCXXRJBB(isSix, is2013, sbsbdm);
//                            intent.setClass(this, DeviceDetailsActivity.class);
//                            intent.putExtra("ZZTYPE", "FZSB");
//                            intent.putExtra("FZSB", (FZBHSB) o);
//                            if (bkxxList != null && bkxxList.size() > 0)
//                                intent.putExtra("BKXX", (Serializable) bkxxList);
//                            if (rjbbList != null && rjbbList.size() > 0)
//                                intent.putExtra("BHXHRJBB", (Serializable) rjbbList);
//                            intent.putExtra("saoma", "");
//                            intent.putExtra("STATE", "C");
//                        }
//                    }
//                }
//            }
//            startActivity(intent);
//        }
//    }
//}
