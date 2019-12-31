package cn.com.sgcc.dev.net.retrofit;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @Version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/4/19
 */
public class ProgressBean {

    private long bytesRead;
    private long contentLength;
    private boolean done;

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
