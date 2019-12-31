package cn.com.sgcc.dev.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * <p>@description:单/多文件下载</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/3/8 0008
 */

public interface FileDownloadService {

    @POST("/resource/example.zip")
    Call<ResponseBody> downloadFileWithFixedUrl();

    @POST
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);

    //大文件耗时操作
    @Streaming
    @POST
    Call<ResponseBody> downloadFileWithDynamicUrlAsync(@Url String fileUrl, @Header("Cookie")String head, @Body Object o);

    //大文件耗时操作
    @Streaming
    @FormUrlEncoded
    @POST
    Call<ResponseBody> downloadFileWithDynamicUrlAsync1(@Url String fileUrl, @Header("Cookie")String head, @Field("items") Object o);
}
