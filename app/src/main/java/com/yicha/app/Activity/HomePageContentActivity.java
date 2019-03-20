package com.yicha.app.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yicha.app.Adapter.SeachViewPagerAdapter;
import com.yicha.app.Fragment.HomePageContentFragment;
import com.yicha.app.R;

import java.util.ArrayList;
import java.util.List;

public class HomePageContentActivity extends AppCompatActivity {
    private ViewPager MyShouCang_ViewPager;
    private TabLayout MyShouCang__tb_title;
    private List<Fragment> pagerList;
    private SeachViewPagerAdapter seachViewPagerAdapter;
    private String[] titles;
    private TextView Title;
    private FrameLayout Add_FrameLayout;
    private FrameLayout back_Fl;
    private int State;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_content);
        initView();
        initDate();
        State = getIntent().getIntExtra("state",0);
        Log.i("asss","state=="+State);
        MyShouCang_ViewPager.setCurrentItem(State);
    }
    protected void initDate() {
        titles = getResources().getStringArray(R.array.MyHeZuo);
        pagerList = new ArrayList();
        for (int i = 0; i <  titles.length; i++) {
            HomePageContentFragment myCooperationFragment = new HomePageContentFragment();
            Bundle localBundle = new Bundle();
            localBundle.putInt("data", i);
            myCooperationFragment.setArguments(localBundle);
            pagerList.add(myCooperationFragment);
        }
        seachViewPagerAdapter = new SeachViewPagerAdapter(getSupportFragmentManager(), HomePageContentActivity.this,  pagerList,  titles);
        MyShouCang_ViewPager.setAdapter( seachViewPagerAdapter);
        MyShouCang_ViewPager.setOffscreenPageLimit(4);
        MyShouCang__tb_title.setupWithViewPager( MyShouCang_ViewPager);
        MyShouCang_ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int paramAnonymousInt) {
            }

            public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2) {
                if (paramAnonymousInt1 == 0) ;
                while ((paramAnonymousInt1 == 1) || (paramAnonymousInt1 != 2))
                    return;
            }

            public void onPageSelected(int paramAnonymousInt) {
            }
        });
    }

    private void initView() {
        MyShouCang__tb_title = ((TabLayout) findViewById(R.id.MyShouCang__tb_title));
        Add_FrameLayout = ((FrameLayout) findViewById(R.id.Add_FrameLayout));
        back_Fl = ((FrameLayout) findViewById(R.id.back_Fl));
        MyShouCang_ViewPager = ((ViewPager) findViewById(R.id.MyShouCang_ViewPager));
        Title = (TextView) findViewById(R.id.Title);
        Add_FrameLayout.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(HomePageContentActivity.this, TheStoreSearchActivity.class);
            startActivity(intent);
        });
        back_Fl.setOnClickListener(v -> finish());

    }

}
