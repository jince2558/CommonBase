package com.baidu.push.ommon_base.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomEdittext extends AppCompatEditText {

    private static final String TAG = CustomEdittext.class.getSimpleName();
    private int contentNum = 0;

    public CustomEdittext(Context context) {
        super(context);
    }

    public CustomEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void removeTextChangedListener(TextWatcher watcher) {
        super.removeTextChangedListener(watcher);
        Log.e(TAG, "removeTextChangedListener: 删除?");
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        Log.e(TAG, "onTextChanged: CharSequence = " + text.toString());
        Log.e(TAG, "onTextChanged: start = " + start);
        Log.e(TAG, "onTextChanged: lengthBefore = " + lengthBefore);
        Log.e(TAG, "onTextChanged: lengthAfter = " + lengthAfter);

    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        Log.e(TAG, "onFocusChanged: = 焦点发生改变");
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        Log.e(TAG, "onSelectionChanged:  selStart= " + selStart);
        Log.e(TAG, "onSelectionChanged:  selEnd= " + selEnd);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
