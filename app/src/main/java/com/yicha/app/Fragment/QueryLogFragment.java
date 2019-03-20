package com.yicha.app.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yicha.app.Adapter.QueryLog_XRecycleViewAdapter;
import com.yicha.app.BaseApplication;
import com.yicha.app.Entity.QueryLog_Entity;
import com.yicha.app.Presenter.QueryLogPresenter;
import com.yicha.app.R;
import com.yicha.app.View.QueryLogContact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dyc.commlibrary.fragment.BaseFragment;

/**
  * @methob: QueryLogFragment
  * @description:Activity作用描述
 */

public class QueryLogFragment extends BaseFragment implements QueryLogContact.View, XRecyclerView.LoadingListener {
    @BindView(R.id.xrecycleView)
    XRecyclerView xrecycleview;
    Unbinder unbinder;
    private QueryLog_XRecycleViewAdapter queryLog_xRecycleViewAdapter;
    private int page = 0;
    private int pagesize = 10;
    private QueryLogContact.Presenter presenter;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.querylog_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        presenter.requestDatalis();
        return view;
    }

    private void initView() {
        presenter = new QueryLogPresenter(this);
        queryLog_xRecycleViewAdapter = new QueryLog_XRecycleViewAdapter(getActivity());
        xrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrecycleview.setAdapter(queryLog_xRecycleViewAdapter);
        xrecycleview.setLoadingListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public String getToken() {
        return BaseApplication.getInstance().getToken();
    }

    @Override
    public int getUid() {
        return BaseApplication.getInstance().getUserId();
    }

    @Override
    public void loadDataSuccess() {
        xrecycleview.refreshComplete();
        xrecycleview.loadMoreComplete();
    }

    @Override
    public void loadMoreList(List<QueryLog_Entity> paramList) {
        queryLog_xRecycleViewAdapter.addData(paramList);
    }

    @Override
    public void refreshList(List<QueryLog_Entity> paramList) {

        queryLog_xRecycleViewAdapter.setData(paramList);
    }

    @Override
    public void showOrHideLoadMore(boolean paramBoolean) {

        xrecycleview.setLoadingMoreEnabled(paramBoolean);
    }

    @Override
    public void onRefresh() {
        page = 0;
        presenter.requestDatalis();
    }

    @Override
    public void onLoadMore() {
        page += 10;
        presenter.requestDatalis();
    }
}
