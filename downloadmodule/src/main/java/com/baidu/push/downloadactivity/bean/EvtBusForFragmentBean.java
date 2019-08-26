package com.baidu.push.downloadactivity.bean;

import com.baidu.push.downloadactivity.adapter.DownloadTheAttachmentBaseAdapter;

public class EvtBusForFragmentBean {
    //int position, final String tag, final MyViewHolder holder, final String fileTag
    private int position;
    private String tag;
    private DownloadTheAttachmentBaseAdapter.MyViewHolder holder;
    private String fileTag;

    public EvtBusForFragmentBean(int position, String tag, DownloadTheAttachmentBaseAdapter.MyViewHolder holder, String fileTag) {
        this.position = position;
        this.tag = tag;
        this.holder = holder;
        this.fileTag = fileTag;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public DownloadTheAttachmentBaseAdapter.MyViewHolder getHolder() {
        return holder;
    }

    public void setHolder(DownloadTheAttachmentBaseAdapter.MyViewHolder holder) {
        this.holder = holder;
    }

    public String getFileTag() {
        return fileTag;
    }

    public void setFileTag(String fileTag) {
        this.fileTag = fileTag;
    }
}
