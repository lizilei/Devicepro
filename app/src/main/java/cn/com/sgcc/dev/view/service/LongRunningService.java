package cn.com.sgcc.dev.view.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.model2.StoreFile;
import cn.com.sgcc.dev.utils.DataStoreAsyncTask;
import cn.com.sgcc.dev.utils.FileUtils;
import cn.com.sgcc.dev.utils.TimeUtil;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2019/1/21
 */

public class LongRunningService extends Service {

    private long firstTime;//记录第一次操作的时间+
    private Timer timer;
    private MyTimerTask timerTask;
    private MyBinder myBinder = new MyBinder();

    //备份文件相关类
    private StoreFile storeFile;
    private List<StoreFile> storeFileList = new ArrayList<>();
    //判断是不是正在备份
    private boolean isStore;

    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (storeFileList == null)
                storeFileList = new ArrayList<>();
            //写入备份的数据内容
            File file = new File(Constants.APP_STORE);
            if (file.exists()) {
                storeFileList = (List<StoreFile>) FileUtils.readObject(file);
            }
            storeFileList.add(0, storeFile);
            FileUtils.writeObject(storeFileList, new File(Constants.APP_STORE));
            isStore = false;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        timer = new Timer();
        timerTask = new MyTimerTask();
        firstTime = System.currentTimeMillis();
        timer.schedule(timerTask, 0, 1000);
        return myBinder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        if (timer != null)
            timer.cancel();
        if (isStore) {
            //正在备份且没有备份完，删除自动备份的文件
            FileUtils.deleteFileSafely(new File(storeFile.getFilePath()));
        }
        return super.onUnbind(intent);
    }


    public class MyBinder extends Binder {

        public LongRunningService getService() {
            return LongRunningService.this;
        }
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            if (System.currentTimeMillis() - firstTime > Constants.store_time) {
                firstTime = System.currentTimeMillis();//重置开始时间
                storeFile = new StoreFile();
                String storeTime = TimeUtil.getCurrentTime();
                String storeName = "autoStore" + storeTime;
                String filePath = Constants.APP_DATA_STORE + "/";
                storeFile.setFileName(storeName);
                storeFile.setFilePath(filePath + storeName + ".zip");
                storeFile.setFileTime(storeTime);
                isStore = true;

                new DataStoreAsyncTask(false, storeName, filePath, mHander).execute();
            }
        }
    }
}
