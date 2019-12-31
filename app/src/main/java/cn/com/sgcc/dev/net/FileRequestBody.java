package cn.com.sgcc.dev.net;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * <p>@description:扩展okhttp请求体，实现上传时的进度提醒</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/3/23 0023
 */

public final class FileRequestBody<T> extends RequestBody {

    /**
     * 实际请求体
     */
    private RequestBody requestBody;

    /**
     * 上传回调接口
     */
    private RetrofitCallBack<T> callBack;

    /**
     * 包装完成的BufferedSink
     */
    private BufferedSink bufferedSink;

    public FileRequestBody(File file, RetrofitCallBack<T> callBack) {
        this.requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        this.callBack = callBack;
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(sink(sink));
        }

        //写入
        requestBody.writeTo(bufferedSink);

        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //当前写入字节数
            long bytesWritten = 0L;
            //总字节长度
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    //获取contentLength值，后续不在调用
                    contentLength = contentLength();
                }
                //增加当前写入的字节数
                bytesWritten += byteCount;
                //回调
                callBack.onLoading(contentLength, bytesWritten);
            }
        };
    }
}
