package cn.com.sgcc.dev.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <p>@description:回调RetrofitCallBack</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/3/23 0023
 */

public abstract class RetrofitCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(call, response);
        } else {
            onFailure(response.message());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        String msg;
        if (t instanceof SocketTimeoutException){
            msg="连接超时请重试！";
        }else if (t instanceof ConnectException){
            msg="服务器连接失败，请稍候重试！";
        }else if (t instanceof RuntimeException){
            msg="运行异常啦！";
        }else {
            msg=t.getMessage();
        }
        onFailure(msg);
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public abstract void onFailure(String msg);

    public abstract void onLoading(long total, long progress);
}
