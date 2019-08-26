package com.baidu.push.ommon_base.base;

import com.baidu.push.ommon_base.http.RetrofitClient;
import com.baidu.push.ommon_base.mvp.IView;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V extends IView> {
    protected WeakReference<V> weakReference;
    // 管理订阅关系，用于取消订阅
    protected CompositeDisposable compositeDisposable;

    /**
     * 绑定view，一般在初始化中调用该方法
     * @param view view
     */
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        weakReference.get();
    }
    /**
     * 解绑
     */
    public void detachView() {
        this.weakReference = null;
        unsubscribe();
    }
    public V getView() {
        if (weakReference != null) {
            return weakReference.get();
        } else {
            return weakReference.get();
        }
    }
    /**
     * 添加订阅事件.为了防止在网络请求时 ui层已经销毁时的处理
     */
    public void addSubscribe(Observable observable, DisposableObserver<?> observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        DisposableObserver baseObserver = (DisposableObserver) observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
        compositeDisposable.add(baseObserver);
    }

    /**
     * 取消订阅
     */
    public void unsubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
    public <T> T creat(final Class<T> service) {
        return RetrofitClient.getInstance().getRetrofit().create(service);
    }

}
