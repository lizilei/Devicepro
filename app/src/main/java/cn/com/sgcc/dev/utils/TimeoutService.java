package cn.com.sgcc.dev.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;

import cn.com.sgcc.dev.view.activity.LoginActivity;

/**
 * <p>@description:</p>
 *
 * @author tanqiu
 * @version 1.0.0
 * @since 2018/1/30
 */

public class TimeoutService extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    boolean isrun = true;

    @Override
    public void onCreate() {
        Log.e("标记","BindService-->onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("标记","BindService-->onStartCommand()");
        forceApplicationExit();
        return super.onStartCommand(intent, flags, startId);

    }

    private void forceApplicationExit()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityListUtil.getInstence().cleanActivityList();
                stopSelf();
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isrun = false;
    }

}