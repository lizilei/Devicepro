package cn.com.sgcc.dev.net;


import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lilizilei on 2018/3/21.
 */

public abstract class MyCallBack<T extends BaseCallModel> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.raw().code() == 200) {  //服务器有合理响应
            if (response.body().code == 0) {
                onSuc(response);
            } else if (response.body().code == 1) {
                onFail("用户名或密码错误！");
            } else if (response.body().code == 2) {
                onAutoLogin();
            } else {
                onFail(response.body().msg);
            }
        } else {//失败响应
            onFail(response.body().msg);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        String msg;
        if (t instanceof SocketTimeoutException) {
            msg = "连接超时请重试！";
        } else if (t instanceof ConnectException) {
            msg = "服务器连接失败，请稍候重试！";
        } else if (t instanceof RuntimeException) {
            msg = "运行异常啦！";
        } else {
            msg = t.getMessage();
        }
        onFail(msg);
    }

    public abstract void onSuc(Response<T> response);

    public abstract void onFail(String msg);

    public abstract void onAutoLogin();

}
