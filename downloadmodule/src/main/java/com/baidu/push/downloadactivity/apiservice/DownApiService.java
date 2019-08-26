package com.baidu.push.downloadactivity.apiservice;

import com.baidu.push.downloadactivity.bean.AnnouncementURLData;
import com.baidu.push.downloadactivity.bean.DownLoadBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DownApiService {

    //    http://210.73.87.74:8997/bjnkprovider/router?v=1.0&method=bjnk.system.noticeDetails&id=294f5260-ab9f-4951-84b9-5e9a3c3e6948
    @GET("/bjnkprovider/router")
    Observable<DownLoadBean> getFileConten(@Query("v") String v,
                                           @Query("method") String method,
                                           @Query("id") String id);

    //http://210.73.87.74:8997/bjnkprovider/router?v=1.0&method=bjnk.system.file&id=

    @GET("/bjnkprovider/router")
    Observable<AnnouncementURLData> getWebServiceFilePath(@Query("v") String v,
                                                          @Query("method") String method,
                                                          @Query("id") String id);

}
