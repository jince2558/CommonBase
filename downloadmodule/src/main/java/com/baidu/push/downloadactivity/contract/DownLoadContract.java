package com.baidu.push.downloadactivity.contract;

import com.baidu.push.downloadactivity.bean.AnnouncementURLData;
import com.baidu.push.downloadactivity.bean.DownLoadBean;
import com.baidu.push.ommon_base.mvp.IView;

public interface DownLoadContract {
    interface View extends IView {
        void getResult(DownLoadBean downLoadBean);

        void onError(Throwable throwable);

        void getFileIdResult(AnnouncementURLData announcementURLData);

    }
    interface FragmentView extends IView {
        void getFileIdResult(AnnouncementURLData announcementURLData);

        void onError(Throwable throwable);
    }

    interface Presenter {
        void getFileData(String id);

    }


}
