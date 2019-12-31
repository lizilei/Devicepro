package cn.com.sgcc.dev.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;

import com.zhy.autolayout.AutoLayoutActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.utils.ActivityListUtil;
import cn.com.sgcc.dev.utils.AppUtils;
import cn.com.sgcc.dev.utils.AppsUtils;
import cn.com.sgcc.dev.utils.CrashHandler;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.service.LongRunningService;
import cn.com.sgcc.dev.view.service.LongRunningService.MyBinder;


/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/16
 */

public class BaseActivity extends AutoLayoutActivity {

    private boolean activityIsActive;
    private boolean flag;

    // 都是static声明的变量，避免被实例化多次；因为整个app只需要一个计时任务就可以了。
    private static Timer mTimer; // 计时器，每1秒执行一次任务
    private static MyTimerTask mTimerTask; // 计时任务，判断是否未操作时间到达5s
    private static long mLastActionTime; // 上一次操作时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //在onCreate()的时候，判断bundle里面是否有数据，如果有数据，进行赋空处理
        //防止切换系统字体大小和字体样式导致App崩溃或者界面错乱的问题
        if (null != savedInstanceState) {
            savedInstanceState = null;
        }

        super.onCreate(savedInstanceState);

        ActivityListUtil.getInstence().addActivityToList(this);

        //加载并初始化异常捕获类
        CrashHandler.getsInstance().init(this);
    }

    /**
     * 绑定服务
     */
    public void bindServer() {
        Intent intent = new Intent(this, LongRunningService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    /**
     * 解绑
     */
    public void unBind() {
        boolean isBind = AppsUtils.isServiceRunning(this, "cn.com.sgcc.dev.view.service.LongRunningService");
        if (isBind) {
            if (flag == true) {
                unbindService(conn);
                flag = false;
                Log.d("---BASE---","服务解绑成功");
            }
        }else {
            Log.d("---BASE---","服务未绑定");
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinder myBinder = (MyBinder) service;
            LongRunningService lrService = myBinder.getService();
            flag = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    // 每当用户接触了屏幕，都会执行此方法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mLastActionTime = System.currentTimeMillis();
        //Log.e("wanghang", "user action");
        return super.dispatchTouchEvent(ev);
    }

    // 登录成功，开始计时
    protected void startTimer_for() {
        mTimer = new Timer();
        mTimerTask = new MyTimerTask();
        // 初始化上次操作时间为登录成功的时间
        mLastActionTime = System.currentTimeMillis();
        // 每过1s检查一次
        mTimer.schedule(mTimerTask, 0, 1000);
        //Log.e("wanghang", "start timer");
    }

    // 停止计时任务
    protected static void stopTimer() {
        mTimer.cancel();
        //Log.e("wanghang", "cancel timer");
    }


    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
//            Log.e("wanghang", Constants.ch_times+"check time");
            if (ActivityListUtil.getInstence().activityList != null &&
                    ActivityListUtil.getInstence().activityList.size() == 1 &&
                    ActivityListUtil.getInstence().activityList.get(0).getClass().getSimpleName().equals("LoginActivity")) {

            } else {
                // 5s未操作5000
                if (Constants.ch_times == 300001) {
                    Map<String, String> saveMap = new HashMap<>();

                    Map<String, String> txtMap = AppUtils.setTxt(saveMap, "", false);

                    System.out.println(txtMap.toString());
                    String csmax = txtMap.get("csmax") + "";

                    System.out.println(csmax);
                    int max = Integer.parseInt(csmax);
                    if (max > 60000) {
                        Constants.ch_times = max;
                    }

                    //初始化备份时间
                    Constants.store_time = Integer.parseInt(txtMap.get("auto_store") + "") * 60000;
                }
                if (System.currentTimeMillis() - mLastActionTime > Constants.ch_times) {
                    // 退出登录
                    //Log.e("wanghang", "结束了啊");
                    //超时重置
                    Constants.loginout = true;
                    sendBroadcast(new Intent("cn.sgg.finishActivity"));
                    ActivityListUtil.getInstence().cleanActivityList();
                    // 停止计时任务
                    stopTimer();
                    //停止自动备份
                    unBind();
                } else {
//                    Log.e("wanghang", "还剩下"+(System.currentTimeMillis() - mLastActionTime));
                }
            }
        }
    }
}