package com.yicha.app.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yicha.app.Fragment.HomePageFragment;
import com.yicha.app.Fragment.MyFragment;
import com.yicha.app.Fragment.QueryLogFragment;
import com.yicha.app.R;
import com.yicha.app.Utlis.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //声明RadioButton数组
    private RadioButton[] radioButtons;
    //声明线性布局，用于放Fragment
    private List<Fragment> list = new ArrayList<>();
    //自定义变量用于区别不同的Fragmnet
    private int currentTabIndex = 0;
    //声明RadioGroup控件
    private RadioGroup main_radioGroup_title;

    private LinearLayout ma_linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_radioGroup_title = (RadioGroup) findViewById(R.id.main_radioGroup_title);
        ma_linearLayout = (LinearLayout) findViewById(R.id.linearlayout_mainactivity);
        main_radioGroup_title.check(main_radioGroup_title.getChildAt(0).getId());
        initData();
        initTable();
        AppUtil.verifyStoragePermissions(MainActivity.this);
    }
    private void initTable() {
        radioButtons = new RadioButton[main_radioGroup_title.getChildCount()];
        for (int i = 0; i < main_radioGroup_title.getChildCount(); i++) {
            radioButtons[i] = (RadioButton) main_radioGroup_title.getChildAt(i);
        }
        main_radioGroup_title.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < main_radioGroup_title.getChildCount(); i++) {
                    if (radioButtons[i].getId() == checkedId) {
                        switchFragment(i);
                    }
                }
            }
        });
    }

    public void switchFragment(int targetTabIndex) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 定义当前的碎片和将要加载的碎片
        Fragment currentFragment = list.get(currentTabIndex);
        Fragment targetFragment = list.get(targetTabIndex);

        if (!targetFragment.isAdded()) {
            transaction.hide(currentFragment).add(
                    R.id.linearlayout_mainactivity, targetFragment);

        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }

        transaction.commit();
        currentTabIndex = targetTabIndex;
    }

    private void initData() {
        list.add(new HomePageFragment());
        list.add(new QueryLogFragment());
        list.add(new MyFragment());
        getSupportFragmentManager().beginTransaction().add(R.id.linearlayout_mainactivity, list.get(0)).commit();
    }
}
