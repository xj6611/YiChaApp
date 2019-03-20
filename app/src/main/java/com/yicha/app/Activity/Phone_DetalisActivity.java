package com.yicha.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yicha.app.Adapter.PC_Data_XRecycleViewAdapter;
import com.yicha.app.BaseApplication;
import com.yicha.app.Entity.PC_DatalisEntity;
import com.yicha.app.Presenter.Phone_DatalisPresenter;
import com.yicha.app.R;
import com.yicha.app.View.Phone_DatalisContact;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dyc.commlibrary.activity.BaseActivity;

public class Phone_DetalisActivity extends BaseActivity implements Phone_DatalisContact.View, PC_Data_XRecycleViewAdapter.OnRecyclerviewItemClickListener {
    @BindView(R.id.Add_FrameLayout)
    FrameLayout Add_FrameLayout;
    @BindView(R.id.Title)
    TextView Title;
    private int id;
    @BindView(R.id.mLoginTv)
    TextView mLoginTv;
    private String CpuModel;
    private String ScreenResolution;
    private PC_Data_XRecycleViewAdapter pc_data_xRecycleViewAdapter;
    private Phone_DatalisContact.Presenter presenter;
    @BindView(R.id.recycleView)
    XRecyclerView recycleview;
    private HashMap<String, String> stringHashMap;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone__detalis);
        ButterKnife.bind(this);
        initView();
        presenter.requestDatalis();
    }

    private void initView() {
        presenter = new Phone_DatalisPresenter(this);
        id = getIntent().getIntExtra("id", 0);
        title = getIntent().getStringExtra("name");
        ScreenResolution = getIntent().getStringExtra("ScreenResolution");
        CpuModel = getIntent().getStringExtra("CpuModel");
        if (title!=null){
            Title.setText(title);
        }
        pc_data_xRecycleViewAdapter = new PC_Data_XRecycleViewAdapter(this);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(pc_data_xRecycleViewAdapter);
        pc_data_xRecycleViewAdapter.setOnRecyclerviewItemClickListener(this);
    }

    public void DatalisSuccess(PC_DatalisEntity paramPC_DatalisEntity) {
        pc_data_xRecycleViewAdapter.setData(paramPC_DatalisEntity.getOptions());
    }

    public String getID() {
        return title;
    }

    public String getToken() {
        return BaseApplication.getInstance().getToken();
    }

    public int getUid() {
        return BaseApplication.getInstance().getUserId();
    }

    @OnClick({R.id.Add_FrameLayout, R.id.mLoginTv})
    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.Add_FrameLayout:
                finish();
                return;
            case R.id.mLoginTv:
                Intent intent = new Intent();
                intent.setClass(this, Phone_ValuationActivity.class);
                intent.putExtra("name", title);
                intent.putExtra("id", id);
                intent.putExtra("CpuModel", CpuModel);
                intent.putExtra("stringHashMap", stringHashMap);
                intent.putExtra("ScreenResolution", ScreenResolution);
                startActivity(intent);
                finish();
                return;

        }
    }

    @Override
    public void onItemClick(int paramInt, HashMap<String, String> paramHashMap) {
        stringHashMap = paramHashMap;
    }

    @Override
    public void onRecyclerviewItemClick(RecyclerView paramRecyclerView, View paramView, int paramInt) {

    }
}
