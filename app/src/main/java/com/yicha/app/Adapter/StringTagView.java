package com.yicha.app.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.yicha.app.FlowBox.widget.BaseTagView;


public class StringTagView extends BaseTagView<String> {

    public StringTagView(Context context) {
        this(context, null);
    }

    public StringTagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public StringTagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setItem(String item) {
        super.setItem(item);
        textView.setText(item);
    }
}