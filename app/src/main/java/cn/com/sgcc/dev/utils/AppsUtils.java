package cn.com.sgcc.dev.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;


import java.io.DataOutputStream;
import java.io.File;
import java.util.List;

public class AppsUtils {

    /**
     * 允许交互控件反复点击的最小时间间隔
     */
    private static final long INTERVAL = 500;
    /**
     * 记录上次点击的时间
     */
    private static long lastTime;

    /**
     * 获取包名
     */
    public static String getPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取应用名称
     */
    public static String getAppName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.applicationInfo.loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取versionCode
     */
    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取versionName
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 安装apk
     */
    public static void installApk(Context context, String path, String name) {
        File file = new File(path, name);
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, getPackageName(context) + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.parse("file://" + file.toString()),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    /**
     * app是否安装
     */
    public static boolean appInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 打开app
     */
    public boolean launchApp(Context context, String packageName, String activityName) {
        try {
            if (!appInstalled(context, packageName)) {
                return false;
            }
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            if ((activityName == null) || ("".equals(activityName.trim()))) {
                intent.setAction("android.intent.action.MAIN");
            } else {
                intent = new Intent();
                intent.setComponent(new ComponentName(packageName, activityName));
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 是否点击过快（时间间隔工具，可用于按钮等交互控件，防止短时间内反复点击）
     */
    public static boolean isTooFast() {
        long nowTime = System.currentTimeMillis();
        long interval = nowTime - lastTime;
        if (interval > 0 && interval < INTERVAL) {
            return true;
        }
        lastTime = System.currentTimeMillis();
        return false;
    }

    public static boolean upgradeRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd="chmod 777 " + pkgCodePath;
            //切换到root帐号
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return process.waitFor()==0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param ctx         上下文
     * @param serviceName 要查询的服务的名字
     * @return
     */
    public static boolean isServiceRunning(Context ctx, String serviceName) {

        ActivityManager am = (ActivityManager) ctx
                .getSystemService(Context.ACTIVITY_SERVICE);

        //获取系统所有正在运行的服务,最多返回100个
        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            //获取服务的名称
            String className = runningServiceInfo.service.getClassName();

            System.out.println(className);
            // 服务存在
            if (className.equals(serviceName)) {
                return true;
            }
        }

        return false;
    }

}
