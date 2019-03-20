package com.yicha.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.yicha.app.Activity.HomePageContentActivity;
import com.yicha.app.Activity.LoginActivity;
import com.yicha.app.Activity.TheStoreSearchActivity;
import com.yicha.app.Adapter.SeachViewPagerAdapter;
import com.yicha.app.BaseApplication;
import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.UserEntity;
import com.yicha.app.Presenter.LoginPresenter;
import com.yicha.app.R;
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

public class HomePageFragment extends BaseFragment implements LoginContact.View {
    @BindView(R.id.HomePageBanner)
    XBanner HomePageBanner;
    @BindView(R.id.SouSuo_linear)
    LinearLayout SouSuo_linear;
    @BindView(R.id.Phone)
    TextView Phone;
    @BindView(R.id.Pc)
    TextView Pc;
    @BindView(R.id.DianDongChe)
    TextView DianDongChe;
    @BindView(R.id.PingGu_Button)
    Button PingGu_Button;
    private List<String> imgesUrl;
    private String username,password;
    LoginPresenter mPresenter;
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage_layou,container,false);
        ButterKnife.bind(this,view);
        initDate();
        readAccount();
        mPresenter = new LoginPresenter(this);
        mPresenter.requestLogin();
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
    protected void initDate() {
        imgesUrl = new ArrayList<>();
        imgesUrl.add("http://img5.imgtn.bdimg.com/it/u=1583916922,2044831261&fm=26&gp=0.jpg");
        imgesUrl.add("http://img0.imgtn.bdimg.com/it/u=1240484896,3427997286&fm=26&gp=0.jpg");
        imgesUrl.add("http://img0.imgtn.bdimg.com/it/u=567623782,1903625888&fm=11&gp=0.jpg");
        imgesUrl.add("http://img2.imgtn.bdimg.com/it/u=3574466525,3971459281&fm=26&gp=0.jpg");
        imgesUrl.add("http://img5.imgtn.bdimg.com/it/u=1637490575,185284836&fm=26&gp=0.jpg");
        imgesUrl.add("http://img3.imgtn.bdimg.com/it/u=320858833,2589222002&fm=26&gp=0.jpg");
        HomePageBanner.setData(imgesUrl,null);//第二个参数为提示文字资源集合
        HomePageBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                Picasso.with(getActivity()).load(imgesUrl.get(position)) .into((ImageView) view);
            }
        });
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

    @Override
    public String getPhone() {
        return username;
    }

    @Override
    public String getPwd() {
        return password;
    }

    @Override
    public void loginSuccess(UserEntity value) {
        BaseApplication.getInstance().saveUserInfo(value);
    }

    @Override
    public void loginError(BaseEntity value) {
        BaseApplication.getInstance().clearUserInfo();
        Intent intent = new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void loginErrorString(String value) {

    }

    //读取保存在本地的用户名和密码
    public void readAccount() {

        //创建SharedPreferences对象
        SharedPreferences sp = getActivity().getSharedPreferences("info", Context.MODE_APPEND);

        //获得保存在SharedPredPreferences中的用户名和密码
        username = sp.getString("name", "");
        password = sp.getString("pwd", "");

    }
}
