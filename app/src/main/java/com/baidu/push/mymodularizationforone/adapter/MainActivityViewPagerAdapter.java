package com.baidu.push.mymodularizationforone.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainActivityViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> data = new ArrayList<>();

    public MainActivityViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> data) {
        super(fm, behavior);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }
}
