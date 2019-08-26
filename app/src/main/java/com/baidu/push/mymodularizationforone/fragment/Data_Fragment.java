package com.baidu.push.mymodularizationforone.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.push.login.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Data_Fragment extends Fragment {


    public Data_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_, container, false);
    }

}
