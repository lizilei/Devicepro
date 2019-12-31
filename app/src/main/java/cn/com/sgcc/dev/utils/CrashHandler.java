package cn.com.sgcc.dev.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.sgcc.dev.constants.Constants;

/**
 * <p>@description:</p>
 * 应用异常处理类
 *
 * @author lizilei
 * @Version 1.0
 * @Email lizilei_warms@163.com
 * @since 2019/1/9
 */
public class CrashHandler implements UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    public static final boolean DEBUG = true;

    /**
     * 文件名
     */
    public static final String FILE_NAME = "crash";
    private static CrashHandler sInstance = new CrashHandler();
    private UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;


    /**
     * 文件名后缀
     */
    private static final String FILE_NAME_SUFFIX = ".trace";

    public CrashHandler() {

    }

    public static CrashHandler getsInstance() {
        return sInstance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        //得到系统的异常处理器
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前应用异常处理器改为默认的
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context;
    }

    /**
     * 当系统中有未被捕获的异常，系统将会自动调用 uncaughtException 方法
     *
     * @param t 出现未捕获异常的线程
     * @param e 未捕获的异常 ，可以通过e 拿到异常信息
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        //导入异常信息到CRASH-LOG
        try {
            dumpExceptionToSDCard(e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        e.printStackTrace();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        //如果系统提供了默认的异常处理器，则交给系统去结束程序，否则就由自己结束自己
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(t, e);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    /**
     * 将异常信息写入SD卡
     *
     * @param e
     */
    private void dumpExceptionToSDCard(Throwable e) throws IOException {
        //如果SD卡不存在或无法使用，则无法将异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "sdcard unmounted,skip dump exception");
                return;
            }
        }
        File dir = new File(Constants.CRASH_PATH);
        //如果目录下没有文件夹，就创建文件夹
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //得到当前年月日时分秒
        String time = TimeUtil.getCurrentTime2();
        //在定义的Crash文件夹下创建文件
        File file = new File(Constants.CRASH_PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            //写入时间
            pw.println(time);
            //写入手机信息
            pw.print(dumpPhoneInfo());
            pw.println();//换行
            e.printStackTrace(pw);
            pw.close();//关闭输入流
        } catch (Exception e1) {
            Log.e(TAG, "dump crash info failed");
        }
    }

    /**
     * 获取手机各项信息
     */
    private StringBuilder dumpPhoneInfo() throws PackageManager.NameNotFoundException {
        StringBuilder sb = new StringBuilder();

        //得到包管理器
        PackageManager pm = mContext.getPackageManager();
        //得到包对象
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        //写入APP版本号
        sb.append("App Version: " + pi.versionName + "_" + pi.versionCode + "\n");
        //写入 Android 版本号
        sb.append("OS Version: " + Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT + "\n");
        //手机制造商
        sb.append("Vendor: " + Build.MANUFACTURER + "\n");
        //手机型号
        sb.append("Model: " + Build.MODEL + "\n");
        //CPU架构
        sb.append("CPU ABI: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sb.append(Build.SUPPORTED_ABIS + "\n");
        } else {
            sb.append(Build.CPU_ABI + "\n");
        }
        return sb;
    }
}
