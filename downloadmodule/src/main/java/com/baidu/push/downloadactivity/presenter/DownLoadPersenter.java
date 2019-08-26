package com.baidu.push.downloadactivity.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.baidu.push.downloadactivity.adapter.DownloadFragmentAdapter;
import com.baidu.push.downloadactivity.adapter.DownloadTheAttachmentBaseAdapter;
import com.baidu.push.downloadactivity.apiservice.DownApiService;
import com.baidu.push.downloadactivity.bean.AnnouncementURLData;
import com.baidu.push.downloadactivity.bean.DownLoadBean;
import com.baidu.push.downloadactivity.contract.DownLoadContract;
import com.baidu.push.ommon_base.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadTask;

import java.io.File;

import io.reactivex.observers.DisposableObserver;

public class DownLoadPersenter extends BasePresenter<DownLoadContract.View> {
    public DownloadTask task;

    /**
     * 内容数据请求
     */
    @SuppressLint("CheckResult")
    public void getFileData(String id) {
        getView().showLoading();
        addSubscribe(creat(DownApiService.class)
                        .getFileConten("1.0", "bjnk.system.noticeDetails", id),
                new DisposableObserver<DownLoadBean>() {
                    @Override
                    public void onNext(DownLoadBean downLoadBean) {
                        getView().getResult(downLoadBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onError(e);
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }

    /**
     * 获取附件信息
     *
     * @param id
     */
    public void getURLRequest(String id) {
        addSubscribe(creat(DownApiService.class)
                        .getWebServiceFilePath("1.0", "bjnk.system.file", id),
                new DisposableObserver<AnnouncementURLData>() {
                    @Override
                    public void onNext(AnnouncementURLData announcementURLData) {
                        getView().getFileIdResult(announcementURLData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void start(String tag, DownloadTheAttachmentBaseAdapter.MyViewHolder holder, String url, String fileTag) {
        Log.e("看看地址吧", "start:  =  http://210.73.87.74:8997/bjnkprovider/file/"+url );
        GetRequest<File> request = OkGo.<File>get("http://210.73.87.74:8997/bjnkprovider/file/" + url)
                .headers("aaa", "111")
                .params("bbb", 222);
        task = OkDownload.request(tag, request)
                .save()
                .register(new DownloadFragmentAdapter.ListDownloadListener(tag, holder, fileTag));
        task.start();//开始下载
    }
}
