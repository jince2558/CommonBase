package com.baidu.push.ommon_base.base;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
        initView();
    }

    /**
     * 数据获取初始化
     */
    protected abstract void initData();

    /**
     * 控件初始化
     */
    protected abstract void initView();

    /**
     * 获取布局
     * @return
     */
    protected abstract int getLayoutId();



}
