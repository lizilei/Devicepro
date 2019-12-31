package cn.com.sgcc.dev.net;

import java.util.Map;

import cn.com.sgcc.dev.model2.RLST_USER;
import cn.com.sgcc.dev.model2.regist.CzInfo;
import cn.com.sgcc.dev.model2.regist.ResultValueBean;
import cn.com.sgcc.dev.model2.regist.UserBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * <p>@description:请求接口</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2018/3/21
 */

public interface ProjectAPIService {

    @GET
    Call<BaseCallModel<RLST_USER>> doLogin(@Url String url);

    @FormUrlEncoded
    @POST
    Call<BaseCallModel<RLST_USER>> doLogin(@Url String url, @FieldMap Map<String, String> map);

    @POST
    Call<BaseCallModel3<ResultValueBean>> doLoginISC(@Url String url, @Body Object o);

    @GET
    Call<BaseCallModel3<UserBean>> doLogin2(@Url String url,@Header("Cookie")String head);

    @GET
    Call<BaseCallModel3<CzInfo>> getCZCS(@Url String url, @Header("Cookie")String head);
}
