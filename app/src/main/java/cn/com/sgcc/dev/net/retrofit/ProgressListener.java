package cn.com.sgcc.dev.net.retrofit;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @Version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/4/19
 */
public interface ProgressListener {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);
}
