package com.baidu.push.mymodularizationforone.fragment;


import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.push.login.R;
import com.baidu.push.login.R2;
import com.baidu.push.ommon_base.base.BaseMVPFragment;
import com.baidu.push.ommon_base.base.BasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.fragment.app.Fragment;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = "/app/Set_Fragment")
public class Set_Fragment extends BaseMVPFragment {

    @BindView(R2.id.set_fragment_btn)
    Button setFragmentBtn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_set_;
    }

    @Override
    protected void initView(View view) {
        setFragmentBtn.setOnClickListener(v -> {
            ARouter.getInstance()
                    .build("/base/ErrorPager")
                    .withInt("error_code", 1)
                    .navigation();
            getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String data) {
    }

    @Override
    protected BasePresenter creatPresenter() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
