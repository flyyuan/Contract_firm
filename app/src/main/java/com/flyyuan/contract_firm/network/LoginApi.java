package com.flyyuan.contract_firm.network;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Yuan on 2017/5/2.
 * class comment:post网络请求，向服务器提交表单
 * 定义登录接口
 */

public interface LoginApi {
    @FormUrlEncoded
    @POST("a/login?__ajax=true")
    Call<HashMap<String,String>> postLoginFormFields(
            //@Field()参数为表单域名称
            @Field("username") String username,
            @Field("password") String password,
            @Field("mobileLogin") String mobileLogin
    );
}
