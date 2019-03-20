package com.yicha.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yicha.app.Activity.Card_DatatlisActivity;
import com.yicha.app.Activity.LoginActivity;
import com.yicha.app.Activity.PC_DatatlisActivity;
import com.yicha.app.Activity.Phone_DetalisActivity;
import com.yicha.app.Adapter.First_XRecycleViewAdapter;
import com.yicha.app.Adapter.Second_XRecycleViewAdapter;
import com.yicha.app.BaseApplication;
import com.yicha.app.Entity.HoemPageEntity;
import com.yicha.app.Entity.MobleListEntity;
import com.yicha.app.Entity.getMobleListEntity;
import com.yicha.app.Presenter.HomePagePresenter;
import com.yicha.app.R;
import com.yicha.app.View.HoemPageContact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dyc.commlibrary.fragment.BaseFragment;

/**
 * Created by CYY on 2018/11/17.
 */

public class HomePageContentFragment extends BaseFragment implements HoemPageContact.View {
    private TextView text;
    private int data;
    //一级菜单控件
    private XRecyclerView mFirstList;
    //一级菜单适配器
    private First_XRecycleViewAdapter mFirstAdapter;
    //一级菜单数据List
    private List<String> mFirstItemList = new ArrayList<String>();

    //二级菜单控件
    private XRecyclerView mSecondList;
    //二级菜单适配器
    private Second_XRecycleViewAdapter mSecondAdapter;
    //二级菜单数据List
    private List<HoemPageEntity.SonBean> mSecondItemList = new ArrayList<>();
    private List<HoemPageEntity> hoemPageEntities = new ArrayList<>();
    private HashMap<String, List<HoemPageEntity.SonBean>> listHashMap = new HashMap<>();
    private HoemPageContact.Presenter presenter;
    private int page = 0, pagesize = 11;
    private int page2 = 0, pagesize2 = 11;
    private String Model = "苹果";
    private boolean aBoolean = false;
    private List<MobleListEntity> mobleListEntities;
    private int state;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xrv_item, container, false);
        data = getArguments().getInt("data");
        presenter = new HomePagePresenter(this);
        switch (data) {
            case 0:
                state = 0;
                presenter.requestXingHaoHoemPage(false);
                break;
            case 1:
                state = 1;
                presenter.requestPCXingHaoHoemPage(false);
                break;
            case 2:
                state = 2;
                presenter.requestCarXingHaoHoemPage(false);
                break;

        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        mFirstList = (XRecyclerView) view.findViewById(R.id.FirstList);
        mFirstList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (state == 0) {
                    page2 = 0;
                    presenter.requestXingHaoHoemPage(false);
                } else if (state == 1) {
                    page2 = 0;
                    presenter.requestPCXingHaoHoemPage(false);
                }else if (state == 2) {
                    page2 = 0;
                    presenter.requestCarXingHaoHoemPage(false);
                }

            }

            @Override
            public void onLoadMore() {
                if (state == 0) {
                    page2 = page2 + 11;
                    presenter.requestXingHaoHoemPage(false);
                } else if (state == 1) {
                    page2 = page2 + 11;
                    presenter.requestPCXingHaoHoemPage(false);
                } else if (state == 2) {
                    page2 = page2 + 11;
                    presenter.requestCarXingHaoHoemPage(false);
                }
                aBoolean = true;
            }
        });
        mFirstList.setPullRefreshEnabled(true);
        mFirstList.setLoadingMoreEnabled(true);
        mFirstList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mFirstList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mSecondList = (XRecyclerView) view.findViewById(R.id.SecondList);
        mSecondList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                aBoolean = true;
                if (state == 0) {
                    page = 0;
                    presenter.requestHoemPage();
                } else if (state == 1) {
                    page = 0;
                    presenter.requestPCHoemPage();
                }else if (state == 2) {
                    page = 0;
                    presenter.requestCarHoemPage();
                }

            }

            @Override
            public void onLoadMore() {
                aBoolean = true;
                if (state == 0) {
                    page = page + 11;
                    presenter.requestHoemPage();
                } else if (state == 1) {
                    page = page + 11;
                    presenter.requestPCHoemPage();
                }else if (state == 2) {
                    page = page + 11;
                    presenter.requestCarHoemPage();
                }
            }
        });
        mSecondList.setPullRefreshEnabled(true);
        mSecondList.setLoadingMoreEnabled(true);
        mSecondList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mSecondList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mFirstAdapter = new First_XRecycleViewAdapter(getActivity());
        mFirstList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFirstList.setAdapter(mFirstAdapter);
        mFirstAdapter.setOnRecyclerviewItemClickListener((parent, view12, position, name) -> {
            Log.i("asss", "name==" + name);
            page = 0;
            Model = name;
            if (state == 0) {
                presenter.requestHoemPage();
            } else if (state == 1) {
                presenter.requestPCHoemPage();
            }else if (state == 2) {
                presenter.requestCarHoemPage();
            }
//                //选中一级菜单点击位置
            mFirstAdapter.setSelectedPosition(position);
//                //将二级菜单上次的点击位置回置
            mSecondAdapter.setSelectedPosition(0);
//                //刷新点击位置
            mFirstAdapter.notifyDataSetChanged();
            aBoolean = true;
//                //获取一级菜单点击位置数据
//                String ItemCode = mFirstItemList.get(position);
//                //通过点击数据获取二级菜单数据
//                getSecond(ItemCode);
        });
        mSecondAdapter = new Second_XRecycleViewAdapter(getActivity());
        mSecondList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSecondList.setAdapter(mSecondAdapter);
        mSecondAdapter.setOnRecyclerviewItemClickListener((parent, view1, position, id, name) -> {
            mSecondAdapter.setSelectedPosition(position);
            mSecondAdapter.notifyDataSetChanged();
            if (state == 0) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Phone_DetalisActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                startActivity(intent);
            } else if (state == 1) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), PC_DatatlisActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                startActivity(intent);
            }else if (state == 2) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Card_DatatlisActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                startActivity(intent);
            }

        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    public int getUid() {
        return BaseApplication.getInstance().getUserId();
    }

    @Override
    public String getToken() {
        return BaseApplication.getInstance().getToken();
    }

    @Override
    public String getModel() {
        return Model;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getPageSize() {
        return pagesize;
    }

    @Override
    public int getPage2() {
        return page2;
    }

    @Override
    public int getPageSize2() {
        return pagesize2;
    }

    @Override
    public void HomePageSuccess(List<MobleListEntity> value) {
        if (aBoolean == false) {
            if (state == 0) {
                page = 0;
                Model = value.get(0).getModel();
                presenter.requestHoemPage();
            } else if (state == 1) {
                page = 0;
                Model = value.get(0).getModel();
                presenter.requestPCHoemPage();
            }else if (state == 2) {
                page = 0;
                Model = value.get(0).getModel();
                presenter.requestCarHoemPage();
            }

        }
    }

    @Override
    public void refreshList(List<getMobleListEntity> list) {
        mSecondAdapter.setData(list);
    }

    @Override
    public void loadMoreList(List<getMobleListEntity> list) {
        mSecondAdapter.addData(list);
    }

    @Override
    public void showOrHideLoadMore(boolean show) {
        mSecondList.setLoadingMoreEnabled(show);
    }

    @Override
    public void refreshList2(List<MobleListEntity> list) {
        mFirstAdapter.setData(list);
        aBoolean=true;
        Log.i("asss","refreshList2==");
    }

    @Override
    public void loadMoreList2(List<MobleListEntity> list) {
        mFirstAdapter.addData(list);
        Log.i("asss","loadMoreList2==");
    }

    @Override
    public void showOrHideLoadMore2(boolean show) {
        mFirstList.setLoadingMoreEnabled(show);
    }

    @Override
    public void loadDataSuccess() {
        aBoolean = true;
        mSecondList.refreshComplete();
        mSecondList.loadMoreComplete();
    }

    @Override
    public void loadDataSuccess2() {
        aBoolean = true;
        mFirstList.refreshComplete();
        mFirstList.loadMoreComplete();
    }

    @Override
    public void Error() {
        Log.i("asss", "娶你大阿姨");
        showExitDiolog();
    }

    private void showExitDiolog() {
        StyledDialog.buildIosAlert("提示", "登录状态失效，请重新登录！", new MyDialogListener() {
            @Override
            public void onFirst() {
            }

            @Override
            public void onSecond() {
                BaseApplication.getInstance().clearUserInfo();
//                SharedPreferences sp = getActivity().getSharedPreferences("info", MODE_PRIVATE);
//                SharedPreferences.Editor ed = sp.edit();
//                ed.putString("name", "");
//                ed.putString("pwd", "");
//                ed.commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onThird() {
            }
        }).setBtnText("下次再说", "重新登录")
                .show();
    }

}
