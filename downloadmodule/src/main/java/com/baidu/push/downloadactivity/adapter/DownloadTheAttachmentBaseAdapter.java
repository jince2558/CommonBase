package com.baidu.push.downloadactivity.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.push.downloadactivity.bean.DownLoadBean;
import com.lzy.okgo.db.DownloadManager;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;


public abstract class DownloadTheAttachmentBaseAdapter extends RecyclerView.Adapter<DownloadTheAttachmentBaseAdapter.MyViewHolder> {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_FINISH = 1;
    public static final int TYPE_ING = 2;
    private static final String TAG = DownloadTheAttachmentBaseAdapter.class.getSimpleName();
    public List<DownloadTask> values;
    public static SharedPreferences sharedPreferences;
    public DownloadTask task;
    private List<DownLoadBean.DataBean.AnnouncementInfoFileData> data;
    private Context context;
    private View view;
    private int layouif;
    public DownloadTheAttachmentBaseAdapter(Context context, List<DownLoadBean.DataBean.AnnouncementInfoFileData> data, int layouif) {
        this.layouif = layouif;
        this.context = context;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        for (int i = 0; i < data.size(); i++) {
            String getPath = sharedPreferences.getString(data.get(i).getUrl(), "");
            data.get(i).setFilePath(getPath);
            if (!getPath.equals("")) {
                data.get(i).setDowloadName("下载完成");
                data.get(i).setProgressCode(10000);
            }
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(layouif, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        bindData(myViewHolder, i);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public abstract void bindData(MyViewHolder myViewHolder, int position);

    public void updateData(int type) {
        //这里是将数据库的数据恢复
        if (type == TYPE_ALL) values = OkDownload.restore(DownloadManager.getInstance().getAll());
        if (type == TYPE_FINISH)
            values = OkDownload.restore(DownloadManager.getInstance().getFinished());
        if (type == TYPE_ING)
            values = OkDownload.restore(DownloadManager.getInstance().getDownloading());
        notifyDataSetChanged();
    }
    public String createTag(int position) {
        return position + "_" + data.get(position).getUrl();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public String tag;
        private View itemView;
        private View layout;
        private Map<Integer, View> cacheView;

        public MyViewHolder(@NonNull View view) {
            super(view);
            this.itemView = view;
            this.layout = itemView;
            cacheView = new HashMap<Integer, View>();
        }

        public View getView() {
            return itemView;
        }

        public View getView(int resId) {
            //对外提供一个获取ConvertView的方法
            View view = null;
            //如果map中包含当前获取的资源Id
            if (cacheView.containsKey(resId)) {
                //直接从我们的map中根据Key获取我们所需要的view
                view = cacheView.get(resId);
            } else {
                //不包含的话,我们实例化id所对应的view,并添加Map缓存
                view = layout.findViewById(resId);
                cacheView.put(resId, view);
            }
            return view;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }


}
