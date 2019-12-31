package cn.com.sgcc.dev.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Handler;

import com.google.gson.Gson;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.dbUtils.IDaoUtil;
import cn.com.sgcc.dev.model2.regist.UserBean;

/**
 * <p>@description:导出DB文件异步任务</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/21
 */

public class OutputDBAsyncTask extends AsyncTask<Void, Void, Void> {

    private Handler mHandler;
    private Context context;
    private IDaoUtil util;
    private List<String> czList;
    private boolean isOutput;

    public OutputDBAsyncTask(Context context, Handler mHandler) {
        this.context = context;
        this.mHandler = mHandler;
    }

    public OutputDBAsyncTask(Context context, Handler mHandler, List<String> czList, boolean isOutput) {
        this.context = context;
        this.mHandler = mHandler;
        this.czList = czList;
        this.isOutput = isOutput;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (new DaoUtil(context).isJY(czList)) {
            mHandler.obtainMessage(-1).sendToTarget();
            return null;
        }

        //将模板文件copy到导出目录下
        String dbPath = copyDBFile();

        if (dbPath != null && !dbPath.equals("")) {

            util = new DaoUtil(context, true, dbPath);

            //导出db文件,返回需要压缩的附件
            util.outputDB(czList, isOutput);

            //导出附件
            exportAcce(dbPath);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mHandler.obtainMessage(1).sendToTarget();
    }

    /**
     * 导出附件
     *
     * @param dbPath db文件存储路径
     */
    private void exportAcce(String dbPath) {
        //先生成压缩文件
        String fileName = Constants.OUTPUT_PATH + "deviceData_" + TimeUtil.getCurrentTime2() + ".zip";
        Constants.OUT_DB_PATH = fileName;
//        ZipUtils.zipFolder(fileName, Constants.APP_IMG, dbPath);

        //对压缩文件加密
        try {
            CipherUtil.dozip(fileName, Zip4jConstants.COMP_DEFLATE, Zip4jConstants.DEFLATE_LEVEL_NORMAL
                    , Zip4jConstants.ENC_METHOD_AES, "jdbh.2019@app_rlst", Constants.APP_IMG, dbPath, Constants.CRASH_PATH);
        } catch (ZipException e) {
            e.printStackTrace();
        } finally {
            //删除多余的文件
            for (File file : new File(Constants.OUTPUT_PATH).listFiles()) {
                if (file.isFile() && !FileUtils.getExtensionName(file.getName()).equals("zip")) {
                    file.delete();
                }
            }
        }
    }

    /**
     * 拷贝的方法
     */
    private String copyDBFile() {
        //先删除之前存在的文件
        File[] fileList = new File(Constants.OUTPUT_PATH).listFiles();
        if (fileList != null && fileList.length > 0) {
            for (File file : fileList) {
                if (file.isFile() && !FileUtils.getExtensionName(file.getName()).equals("zip")) {
                    file.delete();
                }
            }
        }

        String fileName = "rlst_pad" + TimeUtil.getCurrentTime2();
        File file = new File(Constants.OUTPUT_PATH + fileName + ".db");

        AssetManager manager;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
//            File.createTempFile(fileName, ".db", file);
            manager = context.getAssets();
            is = manager.open("rlst_pad_tmplate.db");//输入流
            fos = new FileOutputStream(file); //输出流
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }

            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
