package com.baidu.push.downloadactivity.bean;

import java.io.Serializable;

/**
 * Created by Lenovo on 2017/9/2.
 */

public class AnnouncementURLData implements Serializable {
    private String code;
    private String data;

    public AnnouncementURLData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
