package com.yicha.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yicha.app.BaseApplication;
import com.yicha.app.Class.Timeutils;
import com.yicha.app.R;

public class StartActivity extends AppCompatActivity {
    private Timeutils timeutils;
    private TextView view;
    private int sun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        view = (TextView) findViewById(R.id.view);

        timeutils = new Timeutils(view);
        timeutils.setCount(3);
        timeutils.startTimer();
        sun = timeutils.getCount();
        timeutils.setOnControlsItemClickListener(new Timeutils.OnCompleteClickListener() {
            @Override
            public void onUpDataItemClick(int time) {
                if (time == 0) {
                    if (sun == 3) {
                        if (!BaseApplication.getInstance().getToken().equals("")) {
                            Intent intent = new Intent();
                            intent.setClass(StartActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Intent intent = new Intent();
                            intent.setClass(StartActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    timeutils.stopTimer();
                }
            }
        });
    }
}
