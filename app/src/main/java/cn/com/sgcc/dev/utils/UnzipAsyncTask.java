package cn.com.sgcc.dev.utils;

import android.os.AsyncTask;
import android.os.Handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.customeView.RoundedRectProgressBar;

/**
 * <p>@description:文件解压异步任务</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/4
 */

public class UnzipAsyncTask extends AsyncTask<Void, Integer, Long> {

    private File mDataSourceFile;
    private Handler mHandler;
    private RoundedRectProgressBar progressBar;

    public UnzipAsyncTask(File mDataSourceFile, Handler mHandler, RoundedRectProgressBar progressBar) {
        this.mDataSourceFile = mDataSourceFile;
        this.mHandler = mHandler;
        this.progressBar = progressBar;
    }

    @Override
    protected Long doInBackground(Void... params) {
        return unzip();
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        mHandler.obtainMessage(1).sendToTarget();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setProgress(0);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (progressBar == null) {
            return;
        }
        progressBar.setProgress(values[0]);
    }

    /**
     *  解压缩
     * @return
     */
    private long unzip() {
        long extractedSize = 0L;
        long originalSize = 0L;
        Enumeration<ZipEntry> entries;
        ZipFile zip = null;

        try {
            zip = new ZipFile(mDataSourceFile);
            originalSize = getOriginalSize(zip);
            publishProgress(0, (int) originalSize);
            entries = (Enumeration<ZipEntry>) zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                File destination = new File(Constants.INPUT_PATH, entry.getName());
                if (!destination.getParentFile().exists()) {
                    destination.getParentFile().mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(destination);
                extractedSize += copy(zip.getInputStream(entry), fos);
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return (extractedSize / originalSize) * 100;
    }

    /**
     * 相关IO流操作
     * @param input
     * @param output
     * @return
     */
    private long copy(InputStream input, OutputStream output) {
        int buffer = 1024 * 8;
        byte[] b = new byte[buffer];
        BufferedInputStream bis = new BufferedInputStream(input, buffer);
        BufferedOutputStream bos = new BufferedOutputStream(output, buffer);
        int count = 0, n;
        try {
            while ((n = bis.read(b, 0, buffer)) != -1) {
                bos.write(b, 0, n);
                count += n;
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * 获取原始文件的大小
     * @param zip
     * @return
     */
    private long getOriginalSize(ZipFile zip) {
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.entries();
        long originalSize = 0;
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.getSize() > 0) {
                originalSize += entry.getSize();
            }
        }
        return originalSize;
    }
}