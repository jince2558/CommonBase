package com.baidu.push.downloadactivity.activity;

import android.app.Application;
import android.os.Environment;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okserver.OkDownload;

public class DownLoadApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        OkGo.getInstance().init(this)
                .setCacheMode(CacheMode.NO_CACHE)
                .setOkHttpClient(builder.build());
        String path = Environment.getExternalStorageDirectory().getPath() + "/Download/";//位置
        OkDownload.getInstance()
                .setFolder(path)//设置全局下载的目录
                .getThreadPool().setCorePoolSize(3);
    }
}
