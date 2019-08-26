package com.baidu.push.login.view;

import com.baidu.push.login.bean.LoaginBean;
import com.baidu.push.ommon_base.mvp.IView;

public interface LoginView extends IView {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    void onSuccess(LoaginBean bean);

    void onError(Throwable throwable);
}

