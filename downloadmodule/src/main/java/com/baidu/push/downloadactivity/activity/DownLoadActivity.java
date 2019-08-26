package com.baidu.push.downloadactivity.activity;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baidu.push.downloadactivity.R;
import com.baidu.push.downloadactivity.bean.AnnouncementURLData;
import com.baidu.push.downloadactivity.bean.DownLoadBean;
import com.baidu.push.downloadactivity.contract.DownLoadContract;
import com.baidu.push.downloadactivity.fragment.DownLoadFragment;
import com.baidu.push.downloadactivity.presenter.DownLoadPersenter;
import com.baidu.push.ommon_base.base.BaseMVPActivity;

import butterknife.BindView;


public class DownLoadActivity extends BaseMVPActivity<DownLoadPersenter> implements DownLoadContract.View {


    private static final String TAG = DownLoadActivity.class.getSimpleName();
    @BindView(R.id.download_title)
    TextView downloadTitle;
    @BindView(R.id.download_framelayout)
    FrameLayout downloadFramelayout;

    @Override
    protected void initView() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(downloadFramelayout.getId(), new DownLoadFragment())
                .commit();
//        presenter.getFileData("294f5260-ab9f-4951-84b9-5e9a3c3e6948");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_down_load;
    }

    @Override
    protected DownLoadPersenter creatPresenter() {
        return new DownLoadPersenter();
    }

    @Override
    public void showLoading() {
        Log.e(TAG, "showLoading: ");
    }

    @Override
    public void hideLoading() {
        Log.e(TAG, "hideLoading: ");
    }

    @Override
    public void getResult(DownLoadBean downLoadBean) {
        Log.e(TAG, "getResult: " + downLoadBean.getCode());
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void getFileIdResult(AnnouncementURLData announcementURLData) {

    }
}
