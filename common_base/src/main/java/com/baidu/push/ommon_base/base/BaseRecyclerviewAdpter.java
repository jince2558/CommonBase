package com.baidu.push.ommon_base.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Lenovo on 2018/12/21.
 */

public abstract class BaseRecyclerviewAdpter<T> extends RecyclerView.Adapter<BaseRecyclerviewAdpter.MyViewHolder> {
    public List<T> data;
    private int layoutRes;
    public Context context;
    private View view;

    public BaseRecyclerviewAdpter(List<T> data, int layoutRes, Context context) {
        this.context = context;
        this.layoutRes = layoutRes;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    /**
     * 更新数据源(传递进来的数据源不为null，并且size大于0)
     *
     * @param data
     */
    public void upData(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据源(传递进来的数据源不为null，并且size大于0)
     */
    public void addData(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public BaseRecyclerviewAdpter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerviewAdpter.MyViewHolder holder, int position) {
        bindData(holder, position);
    }

    public abstract void bindData(MyViewHolder myViewHolder, int position);

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private View layout;
        private Map<Integer, View> cacheView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.layout = itemView;
            cacheView = new HashMap<Integer, View>();
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

    }

}
