package com.baidu.push.mymodularizationforone.contract;

import com.baidu.push.mymodularizationforone.bean.Main_Fragment_BeanData;
import com.baidu.push.ommon_base.mvp.IView;

public interface Main_Fragment_Contract {
    interface View extends IView {
        void getResult(Main_Fragment_BeanData data);

        void onError(Throwable throwable);

    }
}
