package cn.com.sgcc.dev.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//import com.zhy.autolayout.utils.AutoUtils;

import org.greenrobot.greendao.DbUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.dao2.DaoMaster;
import cn.com.sgcc.dev.model2.RLST_USER;
import cn.com.sgcc.dev.model2.regist.DecryptKey;
import cn.com.sgcc.dev.utils.CrashHandler;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/8/18
 */

public class App extends Application {
    private static App mApp;
    private static String pad_id;
    private RLST_USER user;
    private DecryptKey decryptKey;

    public static App getInstance() {
        if (mApp == null) {
            synchronized (App.class) {
                if (mApp == null) {
                    mApp = new App();
                }
            }
        }
        return mApp;
    }

    public DecryptKey getDecryptKey() {
        return decryptKey;
    }

    public void setDecryptKey(DecryptKey decryptKey) {
        this.decryptKey = decryptKey;
    }

    public RLST_USER getUser() {
        return user;
    }

    public void setUser(RLST_USER user) {
        this.user = user;
    }

    public static String getPad_id() {
        return pad_id;
    }

    public static void setPad_id(String pad_id) {
        App.pad_id = pad_id;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //加载并初始化异常捕获类
        CrashHandler.getsInstance().init(this);

        File rootFile = new File(Constants.ROOT_PATH);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File inputFile = new File(Constants.INPUT_PATH);
        if (!inputFile.exists()) {
            inputFile.mkdirs();
        }
        File outputFile = new File(Constants.OUTPUT_PATH);
        if (!outputFile.exists()) {
            outputFile.mkdirs();
        }
        File updateFile = new File(Constants.APP_UPDATE);
        if (!updateFile.exists()) {
            updateFile.mkdirs();
        }
        File accessoryFile = new File(Constants.APP_ACCESSORY);
        if (!accessoryFile.exists()) {
            accessoryFile.mkdirs();
        }
        File imgFile = new File(Constants.APP_IMG);
        if (!imgFile.exists()) {
            imgFile.mkdirs();
        }
        File officeFile = new File(Constants.APP_OFFICE);
        if (!officeFile.exists()) {
            officeFile.mkdirs();
        }

        File pdfFile = new File(Constants.APP_PDF);
        if (!pdfFile.exists()) {
            pdfFile.mkdirs();
        }
        File txtFile = new File(Constants.APP_TXT);
        if (!txtFile.exists()) {
            txtFile.mkdirs();
        }
        File officeHtml = new File(Constants.APP_OFFICE_HTML);
        if (!officeHtml.exists()) {
            officeHtml.mkdirs();
        }
        File defaultSet = new File(Constants.APP_DEFAULT);
        if (!defaultSet.exists()) {
            defaultSet.mkdirs();
        }
        File crashFile = new File(Constants.CRASH_PATH);

        if (!crashFile.exists()) {
            crashFile.mkdirs();
        }
        File storeData = new File(Constants.APP_DATA_STORE);
        if (!storeData.exists()) {
            storeData.mkdirs();
        }
        //初始化执行文件夹
        File dir = new File(Constants.APP_DO);
        //如果目录下没有文件夹，就创建文件夹,执行文件夹初始化
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


}
