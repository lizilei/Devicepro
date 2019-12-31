package cn.com.sgcc.dev.net;

import com.google.gson.GsonBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.com.sgcc.dev.constants.Constants;
import cn.com.sgcc.dev.net.retrofit.ProgressHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/3/8
 */

public class RetrofitUtils {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.ROOT_URL)
            .addConverterFactory(LenientGsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .client(getClient())
            .build();

    public static Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl(Constants.ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient2())
            .callbackExecutor(Executors.newFixedThreadPool(1))
            .build();

    public static Retrofit retrofit3 = new Retrofit.Builder()
            .baseUrl(Constants.ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient3())
            .callbackExecutor(Executors.newFixedThreadPool(1))
            .build();


    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(20, TimeUnit.MINUTES)
                .connectTimeout(20, TimeUnit.MINUTES)
//                .addInterceptor()
//                .addInterceptor()
                .build();
        return client;
    }

    private static OkHttpClient getClient2() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(10, TimeUnit.MINUTES)
                .connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
//                .addInterceptor()
//                .addInterceptor()
                .build();
        return client;
    }

    private static OkHttpClient getClient3() {
        OkHttpClient client = ProgressHelper.addProgress(null)
                .readTimeout(60, TimeUnit.MINUTES)
                .connectTimeout(60, TimeUnit.MINUTES)
                .writeTimeout(60, TimeUnit.MINUTES)
                .build();
        return client;
    }
}