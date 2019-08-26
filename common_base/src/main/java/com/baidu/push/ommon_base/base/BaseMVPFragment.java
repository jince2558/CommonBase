package com.baidu.push.ommon_base.base;

import android.content.Context;
import android.content.Intent;

import com.baidu.push.ommon_base.mvp.IView;

public abstract class BaseMVPFragment<P extends BasePresenter> extends BaseFragment implements IView {
    protected P presenter;

    @Override
    protected void initData() {
        presenter = creatPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    public abstract int getLayoutId();

    protected abstract P creatPresenter();
    public void toNextPage(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }

    }
}
