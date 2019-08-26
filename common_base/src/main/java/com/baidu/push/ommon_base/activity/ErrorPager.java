package com.baidu.push.ommon_base.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.push.ommon_base.R;
import com.baidu.push.ommon_base.base.BaseActivity;



@Route(path = "/base/ErrorPager")
public class ErrorPager extends BaseActivity {
    /**
     * 0 暂无数据,
     * 1 网络异常
     * 2 服务异常
     */
    private int ERROR_DATA = 0;
    private int ERROR_NETWORK = 1;
    private int ERROR_SERVICE = 2;
    private Button noDataBtn;
    private ImageView mImg;
    private TextView mContent;

    @Autowired(name = "error_code")
    int error_code;


    @Override
    protected void initData() {
        ARouter.getInstance().inject(this);
        Log.e("看看数据传递", "initData: " + error_code);
    }

    @Override
    protected void initView() {
        noDataBtn = ((Button) findViewById(R.id.activity_error_btn));
        mImg = ((ImageView) findViewById(R.id.activity_error_img));
        mContent = ((TextView) findViewById(R.id.activity_error_content));
        if (error_code == ERROR_NETWORK) {
            mImg.setImageDrawable(getResources().getDrawable(R.mipmap.error_network));
            mContent.setText("网络异常");
        } else if (error_code == ERROR_SERVICE) {
            mImg.setImageDrawable(getResources().getDrawable(R.mipmap.error_server));
            mContent.setText("服务器异常");
        }
        noDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_error_pager;
    }
}
