package com.baidu.push.ommon_base.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit初始化
 */
public class RetrofitClient {


    //private final String baseURL = "https://www.wanandroid.com";
    //http://210.73.87.74:8997/bjnkprovider/router?v=1.0
// &method=bjnk.system.noticeDetails&id=294f5260-ab9f-4951-84b9-5e9a3c3e6948
    private final String baseURL = "https://www.wanandroid.com";
//    private final String baseURL = "http://210.73.87.74:8997";
//    private final String baseURL = "http://192.168.100.113:8010/Bjnwsys_App/router?";


    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private static RetrofitClient getInstance;

    private RetrofitClient() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.logInterceptor())
                .addInterceptor(InterceptorUtil.headInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                //设置解析器
                .addConverterFactory(GsonConverterFactory.create())
                //只是Rxjava平台
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public static RetrofitClient getInstance() {
            if (getInstance == null) {
            synchronized (RetrofitClient.class) {

                if (getInstance == null) {
                    getInstance = new RetrofitClient();
                }
            }

        }
        return getInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
