package com.baidu.push.downloadactivity.fragment;


import android.util.Log;
import android.view.View;

import com.baidu.push.downloadactivity.R;
import com.baidu.push.downloadactivity.adapter.DownloadFragmentAdapter;
import com.baidu.push.downloadactivity.bean.AnnouncementURLData;
import com.baidu.push.downloadactivity.bean.DownLoadBean;
import com.baidu.push.downloadactivity.bean.EvtBusForFragmentBean;
import com.baidu.push.downloadactivity.contract.DownLoadContract;
import com.baidu.push.downloadactivity.presenter.DownLoadPersenter;
import com.baidu.push.ommon_base.base.BaseMVPFragment;
import com.baidu.push.ommon_base.recyclerItemdecoration.DividerItemDecoration;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static com.baidu.push.downloadactivity.adapter.DownloadTheAttachmentBaseAdapter.TYPE_FINISH;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownLoadFragment extends BaseMVPFragment<DownLoadPersenter> implements DownLoadContract.View {
    private static final String TAG = DownLoadFragment.class.getSimpleName();

    @BindView(R.id.fragment_down_load_recycler)
    RecyclerView fragmentDownLoadRecycler;
    private View view;
    private DownloadFragmentAdapter adapter;
    private EvtBusForFragmentBean evtData;
    public DownLoadFragment() {
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_down_load;
    }
    @Override
    protected DownLoadPersenter creatPresenter() {
        return new DownLoadPersenter();
    }
    @Override
    protected void initView(View view) {
        presenter.getFileData("84cf3989-231f-4909-aa54-8e69ef3da18d");
        fragmentDownLoadRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentDownLoadRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EvtBusForFragmentBean beandata) {
        evtData = beandata;
        if (evtData != null) {
            Log.e(TAG, "onEvent: " + evtData.getFileTag());
            presenter.getURLRequest(evtData.getFileTag());
        }
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getResult(DownLoadBean downLoadBean) {
        Log.e(TAG, "getResult: " + downLoadBean.getData().getAttach().size());
        adapter = new DownloadFragmentAdapter(getActivity(), downLoadBean.getData().getAttach(), R.layout.adapter_download_item);
        adapter.updateData(TYPE_FINISH);
        fragmentDownLoadRecycler.setAdapter(adapter);
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e(TAG, "onError: "+throwable);
    }

    @Override
    public void getFileIdResult(AnnouncementURLData announcementURLData) {
        Log.e(TAG, "getFileIdResult: " + announcementURLData.getData());
        presenter.start(evtData.getTag(), evtData.getHolder(), announcementURLData.getData(), evtData.getFileTag());
    }
}
