package com.baidu.push.ommon_base.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * 自定义Toast方法来防止多次点击多次显示toast内容
 */
public class ToastUtils {
    public static Toast toast;

    public static void makeToast(Context context,String content){
        if (toast == null){
            toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }else {
            toast.setText(content);
        }
        toast.show();
    }
    public static void makeToast(Context context,int content){
        if (toast == null){
            toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }else {
            toast.setText(content);
        }
        toast.show();
    }
    public static void LoopMakeToast(Context context, String content){
        if (toast == null){
            Looper.prepare();
            toast = Toast.makeText(context,content, Toast.LENGTH_SHORT);
            Looper.loop();
        }else {
            toast.setText(content);
        }
        toast.show();
    }
}
