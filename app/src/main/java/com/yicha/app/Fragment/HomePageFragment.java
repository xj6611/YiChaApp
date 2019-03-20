package com.yicha.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stx.xhb.xbanner.XBanner;
import com.yicha.app.Activity.ContentActivity;
import com.yicha.app.Activity.HomePageContentActivity;
import com.yicha.app.Activity.LoginActivity;
import com.yicha.app.Activity.TheStoreSearchActivity;
import com.yicha.app.Adapter.SeachViewPagerAdapter;
import com.yicha.app.BaseApplication;
import com.yicha.app.Entity.BannerEntity;
import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.UserEntity;
import com.yicha.app.Presenter.BannerPresenter;
import com.yicha.app.Presenter.LoginPresenter;
import com.yicha.app.R;
import com.yicha.app.View.BannerContact;
import com.yicha.app.View.LoginContact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dyc.commlibrary.fragment.BaseFragment;

/**
 * Created by CYY on 2018/11/17.
 */

public class HomePageFragment extends BaseFragment implements BannerContact.View {
    @BindView(R.id.HomePageBanner)
    XBanner HomePageBanner;
    @BindView(R.id.SouSuo_linear)
    LinearLayout SouSuo_linear;
    @BindView(R.id.Phone)
    LinearLayout Phone;
    @BindView(R.id.Pc)
    LinearLayout Pc;
    @BindView(R.id.DianDongChe)
    LinearLayout DianDongChe;
    @BindView(R.id.PingGu_Button)
    Button PingGu_Button;
    private List<String> imgesUrl;
    private String username,password;
    BannerContact.Presenter mPresenter;
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage_layou,container,false);
        ButterKnife.bind(this,view);
        readAccount();
        mPresenter = new BannerPresenter(this);
        mPresenter.request();
        return view;
    }
    @OnClick({R.id.SouSuo_linear,R.id.Phone,R.id.Pc,R.id.DianDongChe,R.id.PingGu_Button})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.SouSuo_linear:
//                intent.setClass(getActivity(),IWantToCooperationActivity.class);
//                startActivity(intent);
                break;
            case R.id.Phone:
                intent.setClass(getActivity(),HomePageContentActivity.class);
                intent.putExtra("state",0);
                startActivity(intent);
                break;
            case R.id.Pc:
                intent.setClass(getActivity(),HomePageContentActivity.class);
                intent.putExtra("state",1);
                startActivity(intent);
                break;
            case R.id.DianDongChe:
                intent.setClass(getActivity(),HomePageContentActivity.class);
                intent.putExtra("state",2);
                startActivity(intent);
                break;
            case R.id.PingGu_Button:
                intent.setClass(getActivity(),HomePageContentActivity.class);
                intent.putExtra("state",0);
                startActivity(intent);
                break;

        }
    }
    @Override
    protected void initData() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
    @Override
    public void onResume() {
        super.onResume();
        HomePageBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        HomePageBanner.stopAutoPlay();
    }

    //读取保存在本地的用户名和密码
    public void readAccount() {

        //创建SharedPreferences对象
        @SuppressLint("WrongConstant") SharedPreferences sp = getActivity().getSharedPreferences("info", Context.MODE_APPEND);

        //获得保存在SharedPredPreferences中的用户名和密码
        username = sp.getString("name", "");
        password = sp.getString("pwd", "");

    }

    @Override
    public void Error() {

    }

    @Override
    public void Success(List<BannerEntity> paramList) {
        imgesUrl = new ArrayList();
        for (int i = 0; i < paramList.size(); i++) {
            imgesUrl.add(paramList.get(i).getContent());
        }
        HomePageBanner.setData(this.imgesUrl, null);
        HomePageBanner.loadImage((banner, model, view, position) -> Picasso.with(getActivity()).load(imgesUrl.get(position)).into((ImageView)view));
        HomePageBanner.setOnItemClickListener((banner, model, position) -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(),ContentActivity.class);
            if (position==0){
                intent.putExtra("ty", 1);
                intent.putExtra("title", "江苏省涉案财产价格鉴证操作流程");
                startActivity(intent);
            }else if (position==1){
                intent.putExtra("ty", 1);
                intent.putExtra("title", "江苏省涉案财产价格鉴证条例");
                startActivity(intent);
            }else if (position==2){
                intent.putExtra("ty", 1);
                intent.putExtra("title", "江苏省涉案财产价格鉴证条例");
                startActivity(intent);
            }
        });
    }

    @Override
    public String getToken() {
        return BaseApplication.getInstance().getToken();
    }

    @Override
    public int getUid() {
        return BaseApplication.getInstance().getUserId();
    }
}
