package cn.com.sgcc.dev.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.view.activity.LoginActivity;

/**
 * <p>@description:</p>
 *
 * @author tanqiu
 * @version 1.0.0
 * @since 2018/1/30
 */

public class ActivityListUtil {
    private static ActivityListUtil instence;
    public ArrayList<Activity> activityList;

    public ActivityListUtil() {
        activityList = new ArrayList<Activity>();
    }

    public static ActivityListUtil getInstence() {
        if (instence == null) {
            instence = new ActivityListUtil();
        }
        return instence;
    }

    public void addActivityToList(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    public void removeActivityFromList(Activity activity) {
        if (activityList != null && activityList.size() > 0) {
            activityList.remove(activity);
        }
    }

    public void cleanActivityList() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {
                Activity activity = activityList.get(i);
//                Log.e("数量"+i, "所有名字"+activity.getClass().getSimpleName());
                if (activity != null && !activity.isFinishing()) {
                    if(activity.getClass().getSimpleName().equals("LoginActivity")){
//                        Log.e("数量"+i, "未关闭名字"+activity.getClass().getSimpleName());
                    }else {
//                        Log.e("数量"+i, "关闭名字"+activity.getClass().getSimpleName());
                        removeActivityFromList(activity);
                        activity.finish();
                    }
                }
            }
        }

    }

}