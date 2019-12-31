package cn.com.sgcc.dev.net.retrofit;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @Version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/4/19
 */
public abstract class ProgressHandler {

    protected abstract void sendMessage(ProgressBean progressBean);

    protected abstract void handleMessage(Message message);

    protected abstract void onProgress(long progress, long total, boolean done);

    protected static class ResponseHandler extends Handler{

        private ProgressHandler mProgressHandler;
        public ResponseHandler(ProgressHandler mProgressHandler, Looper looper) {
            super(looper);
            this.mProgressHandler = mProgressHandler;
        }

        @Override
        public void handleMessage(Message msg) {
            mProgressHandler.handleMessage(msg);
        }
    }

}
