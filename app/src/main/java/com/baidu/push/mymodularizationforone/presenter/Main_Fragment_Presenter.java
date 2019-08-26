package com.baidu.push.mymodularizationforone.presenter;

import android.util.Log;

import com.baidu.push.mymodularizationforone.apiService.AppApiService;
import com.baidu.push.mymodularizationforone.bean.Main_Fragment_BeanData;
import com.baidu.push.mymodularizationforone.contract.Main_Fragment_Contract;
import com.baidu.push.ommon_base.base.BasePresenter;

import io.reactivex.observers.DisposableObserver;

public class Main_Fragment_Presenter extends BasePresenter<Main_Fragment_Contract.View> {


    public void getListData() {
        addSubscribe(creat(AppApiService.class).getListData(0), new DisposableObserver<Main_Fragment_BeanData>() {
            @Override
            public void onNext(Main_Fragment_BeanData data) {
                Log.e("走了走了", "??");
                getView().getResult(data);
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

}
