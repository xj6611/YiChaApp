package com.yicha.app.common.base;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import com.yicha.app.common.base.eventbus.EventBusDelegate;
import com.yicha.app.common.base.eventbus.IEventBus;

import java.util.LinkedList;
import java.util.Queue;

import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements BasePresenterView, IEventBus {

    protected final String TAG = this.getClass().getSimpleName();

    private Queue<Disposable> disposableQueue;
    public TextView right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposableQueue = new LinkedList<>();
        if (this instanceof IEventBus) {
            EventBusDelegate.register(this);
        }
    }



    @Override
    public AppCompatActivity getCurrentActivity() {
        return this;
    }

    @Override
    public void addDisposable(Disposable disposable) {
        disposableQueue.add(disposable);
    }

    @Override
    protected void onDestroy() {
        if (disposableQueue != null && disposableQueue.size() > 0) {
            while (disposableQueue.size() > 0) {
                try {
                    disposableQueue.poll().dispose();
                } catch (Throwable e) {
                    Log.e(TAG, "error", e);
                }
            }
        }
        //LoadingDialog.hide();
        super.onDestroy();
        if (this instanceof IEventBus) {
            EventBusDelegate.unregister(this);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View v = getCurrentFocus();

            //如果不是落在EditText区域，则需要关闭输入法
            if (HideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private boolean HideKeyboard(View view, MotionEvent event) {
        if (view != null && (view instanceof EditText)) {

            int[] location = {0, 0};
            view.getLocationInWindow(location);

            //获取现在拥有焦点的控件view的位置，即EditText
            int left = location[0], top = location[1], bottom = top + view.getHeight(), right = left + view.getWidth();
            //判断我们手指点击的区域是否落在EditText上面，如果不是，则返回true，否则返回false
            boolean isInEt = (event.getX() > left && event.getX() < right && event.getY() > top
                    && event.getY() < bottom);
            return !isInEt;
        }
        return false;
    }



}
