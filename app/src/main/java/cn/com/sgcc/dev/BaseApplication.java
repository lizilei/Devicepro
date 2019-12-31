package cn.com.sgcc.dev;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileFilter;

import cn.com.sgcc.dev.application.App;
import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.model2.RLST_USER;
import cn.com.sgcc.dev.model2.regist.DecryptKey;
import cn.com.sgcc.dev.utils.DesUtils;
import cn.com.sgcc.dev.utils.FileUtils;

public class BaseApplication extends Application {

    //应用在平台注册后由平台提供
    public static String appid = "ff808081630a22ba0163387375be0018";

    //应用在平台注册后由平台提供
    public static String secretString = "fbcf63df77cd4279a4a00a8ed3ac4f14";

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private static BaseApplication mApp;
    private static String pad_id;
    private RLST_USER user;
    private DecryptKey decryptKey;

    public static BaseApplication getInstance() {
        if (mApp == null) {
            synchronized (App.class) {
                if (mApp == null) {
                    mApp = new BaseApplication();
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
        BaseApplication.pad_id = pad_id;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 初始化文件
     */
    public void initFile(){

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

        File NSData = new File(Constants.NSData_PATH);
        if (!NSData.exists()) {
            NSData.mkdirs();
        }

        File NSClient = new File(Constants.NSClient_PATH);
        if (!NSClient.exists()) {
            NSClient.mkdirs();
        }

        //初始化执行文件夹
        File dir = new File(Constants.APP_DO);
        //如果目录下没有文件夹，就创建文件夹,执行文件夹初始化
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    //初始化加密的key文件
    public void initDecryptKey() {
        File[] files = new File(Constants.APP_DEFAULT).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                String extName = FileUtils.getExtensionName(pathname.getName());

                return extName.equalsIgnoreCase("key");
            }
        });
        if (files != null && files.length > 0) {
            try {
                DesUtils des = new DesUtils("tongjifenxjiamichuli");
                String decryptKey = des.decrypt(FileUtils.readAppKey(files[0].getAbsolutePath()));
                this.setDecryptKey(new Gson().fromJson(decryptKey, DecryptKey.class));
            } catch (Exception e) {

            }
        }
    }
}
