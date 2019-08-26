package com.baidu.push.mymodularizationforone.fragment;


import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.push.login.R;
import com.baidu.push.login.R2;
import com.baidu.push.mymodularizationforone.adapter.Data_Fragment_RecyclerAdapter;
import com.baidu.push.mymodularizationforone.bean.Main_Fragment_BeanData;
import com.baidu.push.mymodularizationforone.contract.Main_Fragment_Contract;
import com.baidu.push.mymodularizationforone.presenter.Main_Fragment_Presenter;
import com.baidu.push.ommon_base.base.BaseMVPFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = "/app/Main_Fragment")
public class Main_Fragment extends BaseMVPFragment<Main_Fragment_Presenter> implements Main_Fragment_Contract.View {


    private static final String TAG = Main_Fragment.class.getSimpleName();
    @BindView(R2.id.main_fragment_recycler)
    RecyclerView mainFragmentRecycler;

    private Data_Fragment_RecyclerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_;
    }

    @Override
    protected Main_Fragment_Presenter creatPresenter() {
        return new Main_Fragment_Presenter();
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        mainFragmentRecycler.setLayoutManager(manager);
        adapter = new Data_Fragment_RecyclerAdapter(null, R.layout.adapter_mainfragment_item, getActivity());
        mainFragmentRecycler.setAdapter(adapter);
        presenter.getListData();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String data) {
    }
    @Override
    public void showLoading() {
    }
    @Override
    public void hideLoading() {
    }
    @Override
    public void getResult(Main_Fragment_BeanData data) {
        Log.e(TAG, "getResult: " + data.getData().getDatas().size());
        adapter.upData(data.getData().getDatas());
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e(TAG, "Throwable: " + throwable);

    }
}
