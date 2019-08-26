package com.baidu.push.mymodularizationforone;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.push.login.R;
import com.baidu.push.login.R2;
import com.baidu.push.mymodularizationforone.adapter.MainActivityViewPagerAdapter;
import com.baidu.push.mymodularizationforone.fragment.Data_Fragment;
import com.baidu.push.mymodularizationforone.fragment.Main_Fragment;
import com.baidu.push.mymodularizationforone.fragment.My_Fragment;
import com.baidu.push.mymodularizationforone.fragment.Seas_Fragment;
import com.baidu.push.mymodularizationforone.fragment.Set_Fragment;
import com.baidu.push.ommon_base.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


/**
 * 初步搭建一个组件化的框架结构测试自己的需求
 * 第三方SDK版本控制
 */
@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity {


    @BindView(R2.id.main_bottomnavigation)
    BottomNavigationView mainBottomnavigation;
    @BindView(R2.id.main_viewpager)
    ViewPager mainViewpager;
    private MainActivityViewPagerAdapter adapter;
    List<Fragment> data = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        data.add(new Main_Fragment());
        data.add(new Set_Fragment());
        data.add(new Data_Fragment());
        data.add(new Seas_Fragment());
        data.add(new My_Fragment());
        mainViewpager.setOffscreenPageLimit(5);
        adapter = new MainActivityViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, data);
        mainViewpager.setAdapter(adapter);
        mainBottomnavigation.setItemIconTintList(null);
        mainBottomnavigation.setOnNavigationItemSelectedListener(item -> {
            int i = item.getItemId();
            if (i == R.id.tab_main) {
                mainViewpager.setCurrentItem(0);
            } else if (i == R.id.tab_set) {
                mainViewpager.setCurrentItem(1);
            } else if (i == R.id.tab_data) {
                mainViewpager.setCurrentItem(2);
            } else if (i == R.id.tab_seas) {
                mainViewpager.setCurrentItem(3);
            } else if (i == R.id.tab_my) {
                mainViewpager.setCurrentItem(4);
            }
            return true;
        });
        /**
         * viewpager监听器
         */
        mainViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainBottomnavigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
