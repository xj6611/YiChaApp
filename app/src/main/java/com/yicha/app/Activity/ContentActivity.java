package com.yicha.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yicha.app.R;

public class ContentActivity extends AppCompatActivity {
    private TextView Title;
    private TextView content;
    private String title;
    private int ty;
    private FrameLayout Add_FrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Add_FrameLayout = ((FrameLayout)findViewById(R.id.Add_FrameLayout));
        Title = ((TextView)findViewById(R.id.Title));
        content = ((TextView)findViewById(R.id.content));
        title = getIntent().getStringExtra("title");
        ty = getIntent().getIntExtra("ty", 0);
        Title.setText(title);
        if (ty == 1) {
            content.setText(R.string.content1);
        }else if (ty==2){
            content.setText(R.string.content2);
        }else {
            content.setText("网络解析失败");
        }
        Add_FrameLayout.setOnClickListener(v -> finish());
    }
}
