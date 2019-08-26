package com.baidu.push.login.contract;

import com.baidu.push.login.bean.LoaginBean;
import com.baidu.push.ommon_base.mvp.IView;

public interface LoginContract {

    interface View extends IView {

        void loginSuccess(LoaginBean result);

        void onError(Throwable throwable);

    }

}
