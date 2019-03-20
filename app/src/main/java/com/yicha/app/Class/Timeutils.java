package com.yicha.app.Class;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by CYY on 2018/11/30.
 */

public class Timeutils {
    private static String  TAG = "<<<";
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private Handler mHandler = null;
    private  int count = 3;
    private boolean isPause = false;
    private static int delay = 1000;  //1s
    private static int period = 1000;  //1s
    private static final int UPDATE_TEXTVIEW = 0;
    private OnCompleteClickListener OnControlsItemClickListener;
    TextView mTextView;
    public Timeutils(final TextView mTextView){
        this.mTextView=mTextView;
        mHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case UPDATE_TEXTVIEW:
                        mTextView.setText(msg.arg1+"/秒");
                        break;
                    default:
                        break;
                }
            }
        };
    }
    //TODO 设置item单击监听
    public void setOnControlsItemClickListener(OnCompleteClickListener onControlsItemClickListener) {
        this.OnControlsItemClickListener = onControlsItemClickListener;
    }

    public  int getCount() {
        return count;
    }

    public  void setCount(int count) {
        this.count = count;
    }

    //TODO RecycleView的item单击事件需要实现的方法
    public interface OnCompleteClickListener {
        void onUpDataItemClick(int time);
    }
    public void puseTimer(){
        isPause = !isPause;
    }
    public void startTimer(){
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    if (count < (count+1)&&count>0) {
                        count--;
                        Log.i("asss", "timeing==" + count);
                        //(任务内延时)
                        //每隔1s实现定时操作更改ui页面的数字
                        Message msg = mHandler.obtainMessage();
                        msg.what = UPDATE_TEXTVIEW;
                        msg.arg1 = count;
                        mHandler.sendMessage(msg);
                        try {
                            // 每100毫秒更新一次位置
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        OnControlsItemClickListener.onUpDataItemClick(count);
                    } else {
                        mTimer.cancel();
                        mTimer = null;
                        //计时到10秒后关闭此定时器，重置标志位，重置计时0
                        mHandler.removeCallbacks(this);
                        count = 4;
                    }
                }
            };
        }

        if(mTimer != null && mTimerTask != null )
            mTimer.schedule(mTimerTask, delay, period);

    }

    public void stopTimer(){

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        count = 0;

    }
    public void sendMessage(int id){
        if (mHandler != null) {
            Message message = Message.obtain(mHandler, id);
            mHandler.sendMessage(message);
        }
    }
}
