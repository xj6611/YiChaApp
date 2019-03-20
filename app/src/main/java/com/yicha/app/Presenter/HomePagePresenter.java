package com.yicha.app.Presenter;


import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.yicha.app.Activity.LoginActivity;
import com.yicha.app.BaseApplication;
import com.yicha.app.Entity.Base2Entity;
import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.HoemPageEntity;
import com.yicha.app.Entity.MobleListEntity;
import com.yicha.app.Entity.UserEntity;
import com.yicha.app.Entity.getMobleListEntity;
import com.yicha.app.RetrofitServiceApi.RetrofitClient;
import com.yicha.app.RetrofitServiceApi.RetrofitService;
import com.yicha.app.View.HoemPageContact;
import com.yicha.app.View.LoginContact;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public class HomePagePresenter implements HoemPageContact.Presenter {
    HoemPageContact.View view;

    public HomePagePresenter(HoemPageContact.View view) {
        this.view = view;
    }

    @Override
    public void requestHoemPage() {
        view.showProgress(null);
        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
        Log.i("asss","page=="+view.getPage()+"pagesize=="+view.getPageSize());
        service.HoemPage(view.getUid(),view.getToken(),view.getPage(),view.getPageSize(),view.getModel())//获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<BaseEntity<List<getMobleListEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BaseEntity<List<getMobleListEntity>> value) {
                        if (value.getResult() == 1) {
                            if(view.getPage()==0){
                                view.refreshList(value.getData());
                            }else{
                                view.loadMoreList(value.getData());
                            }
                            if(value.getData()!=null) {
                                view.showOrHideLoadMore(value.getData().size()==view.getPageSize());
                            }else{
                                view.showOrHideLoadMore(false);
                            }
                        }else {
                            view.showToast(value.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i("asss","Throwable=="+e);
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

    @Override
    public void requestXingHaoHoemPage(boolean b) {
        if(b){
            view.showProgress(null);
        }
        view.showProgress(null);
        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
        Log.i("asss","page2=="+view.getPage2()+"pagesize2=="+view.getPageSize2());
        service.ModelList(view.getUid(),view.getToken(),view.getPage2(),view.getPageSize2())//获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<BaseEntity<List<MobleListEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BaseEntity<List<MobleListEntity>> value) {
                        if (value.getResult() == 1) {
                            if(view.getPage2()==0){
                                view.HomePageSuccess(value.getData());
                                view.refreshList2(value.getData());
                            }else{
                                view.loadMoreList2(value.getData());
                            }
                            if(value.getData()!=null) {
                                view.showOrHideLoadMore2(value.getData().size()==view.getPageSize());
                            }else{
                                view.showOrHideLoadMore2(false);
                            }
                        } else if (value.getResult() == -1){
                            view.Error();
                        } else {
                            view.showToast(value.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i("asss","Throwable=="+e);
                        view.dismissProgress();
                        view.showNetError();
                    }

                    @Override
                    public void onComplete() {
                        view.loadDataSuccess2();
                        view.dismissProgress();
                    }
                });
    }

    @Override
    public void requestPCHoemPage() {
        view.showProgress(null);
        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
        Log.i("asss","page=="+view.getPage()+"pagesize=="+view.getPageSize());
        service.PCList(view.getUid(),view.getToken(),view.getPage(),view.getPageSize(),view.getModel())//获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<BaseEntity<List<getMobleListEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BaseEntity<List<getMobleListEntity>> value) {
                        if (value.getResult() == 1) {
                            if(view.getPage()==0){
                                view.refreshList(value.getData());
                            }else{
                                view.loadMoreList(value.getData());
                            }
                            if(value.getData()!=null) {
                                view.showOrHideLoadMore(value.getData().size()==view.getPageSize());
                            }else{
                                view.showOrHideLoadMore(false);
                            }
                        } else {
                            view.showToast(value.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i("asss","Throwable=="+e);
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

    @Override
    public void requestCarHoemPage() {
        view.showProgress(null);
        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
        Log.i("asss","page=="+view.getPage()+"pagesize=="+view.getPageSize());
        service.getBikeList(view.getUid(),view.getToken(),view.getPage(),view.getPageSize(),view.getModel())//获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<BaseEntity<List<getMobleListEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BaseEntity<List<getMobleListEntity>> value) {
                        if (value.getResult() == 1) {
                            if(view.getPage()==0){
                                view.refreshList(value.getData());
                            }else{
                                view.loadMoreList(value.getData());
                            }
                            if(value.getData()!=null) {
                                view.showOrHideLoadMore(value.getData().size()==view.getPageSize());
                            }else{
                                view.showOrHideLoadMore(false);
                            }
                        }else {
                            view.showToast(value.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i("asss","Throwable=="+e);
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

    @Override
    public void requestPCXingHaoHoemPage(boolean b) {
        if(b){
            view.showProgress(null);
        }
        view.showProgress(null);
        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
        service.PCModelList(view.getUid(),view.getToken(),view.getPage2(),view.getPageSize2())//获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<BaseEntity<List<MobleListEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BaseEntity<List<MobleListEntity>> value) {
                        if (value.getResult() == 1) {
                            if(view.getPage2()==0){
                                view.HomePageSuccess(value.getData());
                                view.refreshList2(value.getData());
                            }else{
                                view.loadMoreList2(value.getData());
                            }
                            if(value.getData()!=null) {
                                view.showOrHideLoadMore2(value.getData().size()==view.getPageSize());
                            }else{
                                view.showOrHideLoadMore2(false);
                            }
                        } else {
                            view.showToast(value.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i("asss","Throwable=="+e);
                        view.dismissProgress();
                        view.showNetError();
                    }

                    @Override
                    public void onComplete() {
                        view.loadDataSuccess2();
                        view.dismissProgress();
                    }
                });
    }

    @Override
    public void requestCarXingHaoHoemPage(boolean b) {
        if(b){
            view.showProgress(null);
        }
        view.showProgress(null);
        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
        service.getBikeModelList(view.getUid(),view.getToken(),view.getPage2(), view.getPageSize2())//获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<BaseEntity<List<MobleListEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BaseEntity<List<MobleListEntity>> value) {
                        if (value.getResult() == 1) {
                            if(view.getPage2()==0){
                                view.HomePageSuccess(value.getData());
                                view.refreshList2(value.getData());
                            }else{
                                view.loadMoreList2(value.getData());
                            }
                            if(value.getData()!=null) {
                                view.showOrHideLoadMore2(value.getData().size()==view.getPageSize());
                            }else{
                                view.showOrHideLoadMore2(false);
                            }
                        } else {
                            view.showToast(value.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i("asss","Throwable=="+e);
                        view.dismissProgress();
                        view.showNetError();
                    }

                    @Override
                    public void onComplete() {
                        view.loadDataSuccess2();
                        view.dismissProgress();
                    }
                });
    }

}
