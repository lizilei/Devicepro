package cn.com.sgcc.dev.view.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.sgcc.dev.BuildConfig;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.LoadingDialog;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.UserInfo;
import cn.com.sgcc.dev.model2.regist.UserBean;
import cn.com.sgcc.dev.net.BaseCallModel3;
import cn.com.sgcc.dev.net.MyCallBack3;
import cn.com.sgcc.dev.net.ProjectAPIService;
import cn.com.sgcc.dev.net.RetrofitUtils;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.AppsUtils;
import cn.com.sgcc.dev.utils.FileUtils;
import cn.com.sgcc.dev.utils.PreferenceUtils;
import cn.com.sgcc.dev.utils.TimeUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    Context mContext;
    @BindView(R.id.gv_home)
    GridView gvHome;

    private static final int INSTALL_PACKAGES_REQUESTCODE = 1;
    private static final int GET_UNKNOWN_APP_SOURCES = 2;
    private static final int REQUESTPERMISSIONCODE = 3;
    private static final int GET_MANAGE_APPLICATIONS_SETTINGS = 4;
    private static final int RESQUEST_CODE_RESULT = 5;

    @BindView(R.id.iv_bottom)
    ImageView ivBottom;
    @BindView(R.id.bt_check_home)
    ImageView btCheckHome;

    private LoadingDialog progressDialog;

    String UserName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        initEvevt();
        initData();
    }

    public void initView() {

        progressDialog = new LoadingDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        ivBottom.getBackground().setAlpha(216);//0~255透明度值

        btCheckHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(HomeActivity.this, "更多列表");
            }
        });

//		SMT_XJ（专业巡检）
//   SMT_JY（设备检验）
//   SMT_QX（缺陷管理）
//   SMT_TZ（运维记录）
//		Intent intent = new Intent();
//		intent.setAction(Intent.ACTION_VIEW);
//		intent.addCategory("com.nicechina.category.DEFAULT");
//		intent.setData(Uri.parse("spnice://com.tongfen.testdemo/index?functionName=SMT_TZ"));
//		startActivity(intent);
//   SMT_YS（设备验收）
//   SMT_TD（图档资料）
//        SMT_YWZL 运维资料
//        SMT_GJYY 高级应用
//        SMT_YWJL 运维记录
//        SMT_RWZX 任务中心
        gvHome.setAdapter(new MyAdapter(this));
        gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent();
                    if (Constants.isLoginOnLine) {
                        File[] allFiles = new File(Constants.INPUT_PATH).listFiles(new FilenameFilter() {
                            @Override
                            public boolean accept(File dir, String name) {
                                return FileUtils.getExtensionName(name).equals("db");
                            }
                        });
                        if (allFiles.length > 0) {
                            intent.setClass(HomeActivity.this, MainActivitys.class);
                        } else {
                            intent.setClass(HomeActivity.this, DataDownloadActivity.class);
                        }
                    } else {
                        intent.setClass(HomeActivity.this, MainActivitys.class);
                    }

                    startActivity(intent);
                } else if (!isAppInstalled("com.nicechina.nsomclient")) {
                    checkECYW();
                } else if (!AppUtils.checkNetwork(HomeActivity.this)) {
                    ToastUtils.showToast(HomeActivity.this, "该功能暂不支持离线模式...");
                } else {
                    String[] type = {"", "SMT_XJ", "SMT_JY", "SMT_QX", "SMT_TZ", "SMT_YS", "SMT_TD"
                            , "SMT_GFBZ", "SMT_BXFX", "SMT_DZJH", "SMT_YBJH", "SMT_SCD", "SMT_YWZL"
                            , "SMT_YWJL", "SMT_GJYY", "SMT_RWZX"};

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory("com.nicechina.category.DEFAULT");
                    String url = "spnice://com.tongfen.testdemo/index?functionName=" + type[position] + "&loginName=" + UserName;

                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            }
        });
    }

    public void initEvevt() {

    }


    public void initData() {
        if (Constants.isLoginOnLine) {
            getinfo();
            UserName = getIntent().getStringExtra("username");
        }
    }

    /**
     * 获取统分用户信息
     */
    public void getinfo() {
        ProjectAPIService apiService = RetrofitUtils.retrofit.create(ProjectAPIService.class);

        apiService.doLogin2(Constants.ROOT_URL + Constants.GET_USERBEAN, Constants.Cookie).enqueue(new MyCallBack3<BaseCallModel3<UserBean>>() {
            @Override
            public void onSuc(Response<BaseCallModel3<UserBean>> response) {
                if (response.isSuccessful() && response.raw().code() == 200) {
                    PreferenceUtils.setPrefString(HomeActivity.this, "Info", new Gson().toJson(response.body().resultValue));

                    File[] allFiles = new File(Constants.INPUT_PATH).listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return FileUtils.getExtensionName(name).equals("db");
                        }
                    });
                    if (allFiles.length > 0) {//本地存在db
                        IDaoUtil util = new DaoUtil(HomeActivity.this);
                        UserInfo info = new UserInfo();
                        info.setId(Long.parseLong(TimeUtil.getCurrentTime2()));
                        String userInfo = PreferenceUtils.getPrefString(HomeActivity.this, "userInfo", null);
                        String userAccountJson = PreferenceUtils.getPrefString(HomeActivity.this, "Info", "");
                        UserBean ub = new Gson().fromJson(userAccountJson, UserBean.class);

                        info.setUsername(userInfo.split("-")[0]);
                        info.setPassword(userInfo.split("-")[1]);
                        info.setNameInTf(ub.getUserAccount());
                        info.setSsgs(ub.getOrgName());

                        util.coreSave(info);
                    }

                } else {
                    ToastUtils.showLongToast(HomeActivity.this, "登录异常，请重试...");
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtils.showLongToast(HomeActivity.this, msg);
            }

            @Override
            public void onAutoLogin() {

            }
        });
    }

    //安装运维apk（通过调用方法安装，非默认安装）testapp1-debug.apk,NS_V2.1.0.apk
    public void checkECYW() {
        AppUtils.showDialog(this, "该功能需要安装服务插件,是否立即安装？", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow pop = (PopupWindow) v.getTag();
                pop.dismiss();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow pop = (PopupWindow) v.getTag();
                pop.dismiss();

                progressDialog.setTitle("插件初始化中，请稍候！");
                progressDialog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (copyApkFromAssets(HomeActivity.this, "ns_app.dat",
                                Constants.ROOT_PATH + "/test.apk")) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);

                            File file = new File(Constants.ROOT_PATH + "/test.apk");
                            fileUri(mContext, intent, file, "application/vnd.android.package-archive");
                            mContext.startActivity(intent);
                            progressDialog.dismiss();
                        } else {
                            ToastUtils.showLongToast(HomeActivity.this, "初始化失败请重试...");
                            progressDialog.dismiss();
                        }
                    }
                }).start();
            }
        });
    }

    /**
     * 判断当前运行版本 解决7.0+异常问题
     *
     * @param context
     * @param intent
     * @param file
     * @param type
     */
    public static void fileUri(Context context, Intent intent, File file, String type) {
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, type);
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
    }

    public boolean copyApkFromAssets(Context context, String fileName, String path) {

        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }


    //自定义适配器（通过继承BaseAdapter）
    class MyAdapter extends BaseAdapter {
        Context context;//声明适配器中引用的上下文
        //将需要引用的图片和文字分别封装成数组

        int[] images = {R.drawable.home_ledger, R.drawable.home_patrol, R.drawable.home_check, R.drawable.
                home_fault, R.drawable.home_acceptance, R.drawable.home_correct, R.drawable.home_document,
                R.drawable.home_guifan, R.drawable.home_boxing, R.drawable.home_dingzhi, R.drawable.home_yaban,
                R.drawable.home_scd, R.drawable.home_ywzl, R.drawable.home_ywjl, R.drawable.home_gaoji, R.drawable.home_renwu};
        String[] names = {"设备台账", "专业巡检", "设备检验", "缺陷管理", "运维记录", "设备验收", "图档资料", "规范标准", "波形分析"
                , "定值校核", "压板校核", "SCD可视化", "运维资料", "运维记录", "高级应用", "任务中心"};

        //通过构造方法初始化上下文
        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            if (Constants.ISNS) {
                return names.length;//images也可以
            } else {
                return 1;
            }
        }

        @Override
        public Object getItem(int position) {
            return names[position];//images也可以
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //通过布局填充器LayoutInflater填充网格单元格内的布局
            View v = LayoutInflater.from(context).inflate(R.layout.item_home, null);
            //使用findViewById分别找到单元格内布局的图片以及文字
            ImageView iv = (ImageView) v.findViewById(R.id.iv_icon_home);
            TextView tv = (TextView) v.findViewById(R.id.tv_icon_home);
            //引用数组内元素设置布局内图片以及文字的内容
            iv.setImageResource(images[position]);
            tv.setText(names[position]);
            //返回值一定为单元格整体布局v
            return v;
        }
    }

    /**
     * 判断是否是8.0,8.0需要处理未知应用来源权限问题,否则直接安装
     */
    private void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= 26) {
            //    PackageManager类中在Android Oreo版本中添加了一个方法：判断是否可以安装未知来源的应用
//            boolean b = getPackageManager().canRequestPackageInstalls();
            boolean b = true;
            if (b) {
                AppsUtils.installApk(HomeActivity.this, Environment.getExternalStorageDirectory().getPath(), "testapp1-debug.apk");
            } else {
                //请求安装未知应用来源的权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, INSTALL_PACKAGES_REQUESTCODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUESTPERMISSIONCODE);
        }
    }

    //调用之前先检查是否安装com.nicechina.nsomclient
    private boolean isAppInstalled(String uri) {
        PackageManager pm = getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GET_UNKNOWN_APP_SOURCES:
                checkIsAndroidO();
                break;
            case GET_MANAGE_APPLICATIONS_SETTINGS:
                checkIsAndroidO();
                break;
            case RESQUEST_CODE_RESULT:
                if (data != null) {
                    int code = data.getIntExtra("resultCode", 404);
                    Log.e("", code + "");
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private long exitTime = 0; // 退出时间

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showToast(this, "再点一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            // 1. 通过Context获取ActivityManager
            ActivityManager activityManager = (ActivityManager) HomeActivity.this.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

            // 2. 通过ActivityManager获取任务栈
            List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();

            // 3. 逐个关闭Activity
            for (ActivityManager.AppTask appTask : appTaskList) {
                appTask.finishAndRemoveTask();
            }
            // 4. 结束进程
            System.exit(0);
        }
    }
}
