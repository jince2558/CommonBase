package com.baidu.push.mymodularizationforone.apiService;

import com.baidu.push.mymodularizationforone.bean.Main_Fragment_BeanData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AppApiService {
    /**
     * 获取首页文章列表
     * @param
     * @return
     */
    //https://wanandroid.com/article/listproject/0/json
    @GET("/article/listproject/{page}/json")
    Observable<Main_Fragment_BeanData> getListData(@Path("page") int pager);
}
