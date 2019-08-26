package com.baidu.push.mymodularizationforone.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.push.login.R;
import com.baidu.push.mymodularizationforone.bean.Main_Fragment_BeanData;
import com.baidu.push.ommon_base.base.BaseRecyclerviewAdpter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.List;

public class Data_Fragment_RecyclerAdapter extends BaseRecyclerviewAdpter<Main_Fragment_BeanData.DataBean.DatasBean> {


    private ImageView mIcon;
    private TextView mTitle;
    private TextView mContent;

    public Data_Fragment_RecyclerAdapter(List<Main_Fragment_BeanData.DataBean.DatasBean> data, int layoutRes, Context context) {
        super(data, layoutRes, context);
    }


    @Override
    public void bindData(MyViewHolder myViewHolder, int position) {
        mIcon = ((ImageView) myViewHolder.getView(R.id.adapter_mainfragment_icon));
        mTitle = ((TextView) myViewHolder.getView(R.id.adapter_mainfragment_title));
        mContent = ((TextView) myViewHolder.getView(R.id.adapter_mainfragment_content));
        mTitle.setText(data.get(position).getTitle());
        mContent.setText(data.get(position).getDesc());
        Glide.with(context)
                .load(data.get(position).getEnvelopePic())
                .centerCrop()
                //占位符
                .placeholder(R.mipmap.ic_launcher)
                //
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(mIcon);
    }
}
