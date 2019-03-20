package com.yicha.app.Presenter;

import android.util.Log;

import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.MobleListEntity;
import com.yicha.app.Entity.QueryLog_Entity;
import com.yicha.app.RetrofitServiceApi.RetrofitClient;
import com.yicha.app.RetrofitServiceApi.RetrofitService;
import com.yicha.app.View.QueryLogContact;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QueryLogPresenter implements QueryLogContact.Presenter {
    QueryLogContact.View view;

    public QueryLogPresenter(QueryLogContact.View view) {
        this.view = view;
    }

    @Override
    public void requestDatalis() {
        view.showProgress(null);
        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
        service.getQueryLogList(this.view.getUid(), this.view.getToken(), this.view.getPage(), this.view.getPageSize())
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<BaseEntity<List<QueryLog_Entity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BaseEntity<List<QueryLog_Entity>> value) {
                        if (value.getResult() == 1) {
                            if (view.getPage() == 0) {
                                view.refreshList(value.getData());
                            } else {
                                view.loadMoreList(value.getData());
                            }
                            if (value.getData() != null) {
                                view.showOrHideLoadMore(value.getData().size() == view.getPageSize());
                            } else {
                                view.showOrHideLoadMore(false);
                            }
                        } else if (value.getResult() == -1) {
//                            view.Error();
                        } else {
                            view.showToast(value.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.dismissProgress();
                        view.showNetError();
                    }

                    @Override
                    public void onComplete() {
                        view.loadDataSuccess();
                        view.dismissProgress();
                    }
                });
    }
}
