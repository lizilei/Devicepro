package cn.com.sgcc.dev.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * <p>@description:文件上传接口</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/3/8 0008
 */

public interface FileUploadService {
    @Multipart
    @POST
    Call<ResponseBody> upload(@Url String url, @Part MultipartBody.Part file, @QueryMap Map<String,Object>map,@Header("Cookie")String head);
}
