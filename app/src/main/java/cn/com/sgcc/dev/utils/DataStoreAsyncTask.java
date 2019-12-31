package cn.com.sgcc.dev.utils;

import android.os.AsyncTask;
import android.os.Handler;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.Zip4jConstants;

import cn.com.sgcc.dev.constants.Constants;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2019/1/11
 */

public class DataStoreAsyncTask extends AsyncTask<Void, Void, Void> {

    private boolean hasFj;
    private String fileName;
    private String filepath;
    private Handler mHandler;

    public DataStoreAsyncTask(Boolean hasFj, String fileName, String filepath, Handler mHandler) {
        this.hasFj = hasFj;
        this.filepath = filepath;
        this.fileName = fileName;
        this.mHandler = mHandler;
    }

    public DataStoreAsyncTask(Boolean hasFj, String fileName, String filepath) {
        this.hasFj = hasFj;
        this.filepath = filepath;
        this.fileName = fileName;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        store(hasFj, fileName);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mHandler != null)
            mHandler.obtainMessage(4).sendToTarget();
    }

    private void store(boolean hasFj, String fileName) {
        String storePath = filepath + "/" + fileName + ".zip";
        if (hasFj) {
            try {
                CipherUtil.dozip(storePath, Zip4jConstants.COMP_DEFLATE, Zip4jConstants.DEFLATE_LEVEL_NORMAL
                        , Zip4jConstants.ENC_METHOD_AES, "jdbh.2019@app_rlst", Constants.APP_ACCESSORY, Constants.INPUT_PATH);
            } catch (ZipException e) {
                e.printStackTrace();
            }
        } else {
            try {
                CipherUtil.dozip(storePath, Zip4jConstants.COMP_DEFLATE, Zip4jConstants.DEFLATE_LEVEL_NORMAL
                        , Zip4jConstants.ENC_METHOD_AES, "jdbh.2019@app_rlst", Constants.INPUT_PATH);
            } catch (ZipException e) {
                e.printStackTrace();
            }
        }
    }
}
