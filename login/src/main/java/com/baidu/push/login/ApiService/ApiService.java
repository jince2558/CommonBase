package com.baidu.push.login.ApiService;

import com.baidu.push.login.bean.LoaginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/user/login")
    @FormUrlEncoded
    Observable<LoaginBean> getLoginData (@Field("username") String username,
                                         @Field("password") String password);


}
