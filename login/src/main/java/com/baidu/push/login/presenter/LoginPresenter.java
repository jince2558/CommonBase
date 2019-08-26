package com.baidu.push.login.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.baidu.push.login.ApiService.ApiService;
import com.baidu.push.login.bean.LoaginBean;
import com.baidu.push.login.contract.LoginContract;
import com.baidu.push.login.view.LoginView;
import com.baidu.push.ommon_base.base.BasePresenter;
import com.baidu.push.ommon_base.http.RetrofitClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.View> {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    @SuppressLint("CheckResult")
    public void getLoginData(String username, String password) {
        RetrofitClient.getInstance().getRetrofit()
                .create(ApiService.class)
                .getLoginData(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<LoaginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoaginBean loaginBean) {
                        getView().loginSuccess(loaginBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
