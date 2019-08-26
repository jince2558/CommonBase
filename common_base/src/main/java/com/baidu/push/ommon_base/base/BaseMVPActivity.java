package com.baidu.push.ommon_base.base;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.baidu.push.ommon_base.mvp.IView;

public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity implements IView {

    protected P presenter;

    @Override
    protected void initData() {
        presenter = creatPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    protected abstract int getLayoutId();

    protected abstract P creatPresenter();
    /**
     * 渐入渐出的页面跳转 主要是跳转到错误页面
     */
    public void ToNextPage(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    /**
     * 页面返回的 渐入渐出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            this.finish();    //finish当前activity
            overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }

    }
}
