package com.yicha.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.yicha.app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TheStoreSearchActivity extends AppCompatActivity {
    @BindView(R.id.Return_FrameLayout)
    FrameLayout Return_FrameLayout;
    @BindView(R.id.Add_FrameLayout)
    FrameLayout Add_FrameLayout;
    @BindView(R.id.DeleteFrameLayout)
    FrameLayout DeleteFrameLayout;
    @BindView(R.id.Title)
    TextView Title;
    @BindView(R.id.hotflexbox)
    FlexboxLayout hotflexbox;
    @BindView(R.id.historical_recordflexbox)
    FlexboxLayout historical_recordflexbox;
//    private DbDao mDbDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_store_search);
        ButterKnife.bind(this);
        Return_FrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        StatusUtil.setUseStatusBarColor(this, Color.parseColor("#ff76b1fd"), Color.parseColor("#ff76b1fd"));
//        // 第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色
//        StatusUtil.setSystemStatus(this, true, false);
//        mDbDao = new DbDao(this);
//        presenter = new StoreHotWordPresenter(this);
//        historical("");
//        presenter.request();
    }
    }
//    private void historical(String name) {
//        historical_recordflexbox.removeAllViews();
//        List<String> strings = mDbDao.queryData(name);
//        for (int i = 0; i < strings.size(); i++) {
//            final TextView textView = new TextView(this);
//            FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(15, 10, 15, 10);
//            textView.setText(strings.get(i));
////            textView.setTextSize(R.dimen.sp_13);
//            textView.setTextColor(R.color.A333333);
//            textView.setPadding(15, 12, 15, 12);
//            final int finalI = i;
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent();
//                    intent.putExtra("content",textView.getText().toString().trim());
//                    setResult(2, intent);
//                    finish();
//                }
//            });
//            textView.setBackgroundResource(R.drawable.bg_hui2_miaobiao);
//            textView.setLayoutParams(layoutParams);
//            historical_recordflexbox.addView(textView);
//        }
//    }
//
//    @OnClick({R.id.Return_FrameLayout, R.id.Add_FrameLayout, R.id.DeleteFrameLayout})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.Add_FrameLayout:
//                boolean hasData = mDbDao.hasData(Title.getText().toString().trim());
//                if (!hasData) {
//                    mDbDao.insertData(Title.getText().toString().trim());
//                    historical("");
//                }
//                Intent intent = new Intent();
//                intent.putExtra("content",Title.getText().toString().trim());
//                setResult(2, intent);
//                finish();
//                break;
//            case R.id.Return_FrameLayout:
//                finish();
//                break;
//            case R.id.DeleteFrameLayout:
//                mDbDao.deleteData();
//                historical("");
//                break;
//
//        }
//    }
//
//    @Override
//    public int getUid() {
//        return BaseApplication.getInstance().getUserId();
//    }
//
//    @Override
//    public void StoreHotWordSuccess(List<String> list) {
//        for (int i = 0; i < list.size(); i++) {
//            final TextView textView = new TextView(this);
//            FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(15, 10, 15, 10);
//            textView.setText(list.get(i));
//            textView.setPadding(15, 12, 15, 12);
////            textView.setTextSize(R.dimen.sp_13);
//            textView.setTextColor(R.color.A333333);
//            final int finalI = i;
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent();
//                    intent.putExtra("content",textView.getText().toString().trim());
//                    setResult(2, intent);
//                    finish();
//                }
//            });
//            textView.setBackgroundResource(R.drawable.bg_hui2_miaobiao);
//            textView.setLayoutParams(layoutParams);
//            hotflexbox.addView(textView);
//        }
//    }
//}
