package com.baidu.push.downloadactivity.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.push.downloadactivity.R;
import com.baidu.push.downloadactivity.bean.DownLoadBean;
import com.baidu.push.downloadactivity.bean.EvtBusForFragmentBean;
import com.baidu.push.ommon_base.utils.OpenFileUtils;
import com.baidu.push.ommon_base.utils.ToastUtils;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2019/6/26.
 */

public class DownloadFragmentAdapter extends DownloadTheAttachmentBaseAdapter {
    private static final String TAG = DownloadFragmentAdapter.class.getSimpleName();
    static Map<Integer, View> progressMAP = new HashMap<>();
    static Map<Integer, View> testBtnMAP = new HashMap<>();
    private TextView mName;
    private static TextView mDownload;
    private static ProgressBar mProgressBar;
    private List<DownLoadBean.DataBean.AnnouncementInfoFileData> data;
    private String fileTag = "";
    private static Context context;

    public DownloadFragmentAdapter(Context context, List<DownLoadBean.DataBean.AnnouncementInfoFileData> data, int layouif) {
        super(context, data, layouif);
        this.context = context;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    @Override
    public void bindData(final MyViewHolder myViewHolder, final int position) {
        if (position < values.size()) {
            task = values.get(position);
        }
        mName = ((TextView) myViewHolder.getView(R.id.adapter_announcemenetinfo_name));
        mDownload = ((TextView) myViewHolder.getView(R.id.adapter_announcemenetinfo_download));
        mProgressBar = ((ProgressBar) myViewHolder.getView(R.id.version_updata_bgbar));
        final String tag = createTag(position);
        mName.setText(data.get(position).getName());
        mProgressBar.setMax(10000);
        mProgressBar.setProgress(data.get(position).getProgressCode());
        mDownload.setText(data.get(position).getDowloadName());
        mDownload.setOnClickListener(view -> {
            fileTag = data.get(position).getUrl();
            EventBus.getDefault().post(new EvtBusForFragmentBean(position, tag, myViewHolder, fileTag));
        });
        progressMAP.put(position, mProgressBar);
        testBtnMAP.put(position, mDownload);

        myViewHolder.getView().setOnClickListener(view -> {
            String path = sharedPreferences.getString(data.get(position).getUrl(), "");
            String progressTag = sharedPreferences.getString(data.get(position).getUrl() + "code", "");
            if (!path.equals("")) {
                if (OpenFileUtils.fileIsExists(path)) {
                    OpenFileUtils.openFile(context, new File(path));
                } else {
                    OkDownload.getInstance().removeTask(progressTag);
                    mProgressBar = (ProgressBar) progressMAP.get(position);
                    mDownload = (TextView) testBtnMAP.get(position);
                    ToastUtils.makeToast(context, "文件不存在");
                    mDownload.setText("下载");
                    mProgressBar.setProgress(0);
                }
            }
        });
        myViewHolder.setTag(tag);
        if (data.get(position).getUrl() != null) {

        }
        if (OkDownload.getInstance().getTask(data.get(position).getUrl()) != null) {
            mDownload.setText("下载完毕");
            mDownload.setEnabled(false);
        }
    }

    public static void getRefreshData(Progress progress, String fileTag, int position) {
        //本次下载的大小
        String currentSize = Formatter.formatFileSize(context, progress.currentSize);
        //总字节长度
        String totalSize = Formatter.formatFileSize(context, progress.totalSize);
        mProgressBar = (ProgressBar) progressMAP.get(position);
        mDownload = (TextView) testBtnMAP.get(position);
        mProgressBar.setMax(10000);
        mProgressBar.setProgress((int) (progress.fraction * 10000));
        Log.e(TAG, "看看进度 = " + position);
        switch (progress.status) {
            case Progress.NONE:
                Log.e(TAG, "NONE测试进入检查下载是否完成 地址");
                break;
            case Progress.PAUSE:
                break;
            case Progress.ERROR:
                OkDownload.getInstance().removeTask(progress.tag);
                mDownload.setText("下载");
                Log.e(TAG, "ERROR测试进入检查下载是否完成 地址");
                break;
            case Progress.WAITING:
                break;
            case Progress.FINISH:
                Log.e(TAG, "FINISH测试进入检查下载是否完成 地址" + progress.filePath);
                sharedPreferences.edit().putString(fileTag, progress.filePath).commit();
                sharedPreferences.edit().putString(fileTag + "code", progress.tag).commit();
                break;
            case Progress.LOADING:
                Log.e(TAG, "LOADING测试进入检查下载是否完成 地址" + progress.filePath);
                Log.e(TAG, "LOADING测试进入检查下载是否完成 文件名" + progress.fileName);
                break;
            default:
        }
        if ((progress.fraction + "").equals("1.0")) {
            mDownload.setText("下载完毕");
        } else {
            mDownload.setText("正在下载");
        }
        if (progress.status == 5) {
            mProgressBar.setProgress(10000);
        }
    }

    public static class ListDownloadListener extends DownloadListener {
        private MyViewHolder holder;
        private String fileTag;

        public ListDownloadListener(Object tag, MyViewHolder holder, String fileTag) {
            super(tag);
            this.holder = holder;
            this.fileTag = fileTag;
        }

        @Override
        public void onStart(Progress progress) {
        }

        @Override
        public void onProgress(Progress progress) {
            if (tag == holder.tag) {
                getRefreshData(progress, fileTag, holder.getAdapterPosition());
            }
        }

        @Override
        public void onError(Progress progress) {
            Throwable throwable = progress.exception;
            if (throwable != null) throwable.printStackTrace();
        }

        @Override
        public void onFinish(File file, Progress progress) {
        }

        @Override
        public void onRemove(Progress progress) {
        }
    }
}
