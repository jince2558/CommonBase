package com.baidu.push.ommon_base.http;

import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 拦截器
 */
public class InterceptorUtil {

    public static Interceptor headInterceptor() {
        return new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
                builder.addHeader("X-APP-Agent", "corp_zx_app")
                        .addHeader("X-OS", "Android")
//                        .addHeader("X-APP-ID", "20181018000061")
                        .addHeader("X-APP-ID", "20181130000009")
                        .addHeader("X-DEVICE-TYPE", "USERNAME")
                        .addHeader("appId", "281")
                        .addHeader("businessType", "610001");

                String token = "";
                if (!TextUtils.isEmpty(token)) {
                    builder.addHeader("Access-Token", token);
                }
                Request request = builder.build();
                return chain.proceed(request);
            }
        };
    }

    public static Interceptor logInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
