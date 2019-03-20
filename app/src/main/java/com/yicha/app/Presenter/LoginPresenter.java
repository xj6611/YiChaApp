package com.yicha.app.Presenter;


import android.util.Log;

import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.UserEntity;
import com.yicha.app.RetrofitServiceApi.RetrofitClient;
import com.yicha.app.RetrofitServiceApi.RetrofitService;
import com.yicha.app.View.LoginContact;
import com.yicha.app.common.Api;

import dyc.commlibrary.utils.ToastUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public class LoginPresenter implements LoginContact.Presenter {
    LoginContact.View view;

    public LoginPresenter(LoginContact.View view) {
        this.view = view;
    }

    @Override
    public void requestLogin() {
        view.showProgress(null);
//        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
//        Log.i("asss",view.getPhone()+"Pwd"+view.getPwd());
//        service.login(view.getPhone(), view.getPwd())//获取Observable对象
//                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
//                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
//                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
//                .subscribe(new Observer<BaseEntity<UserEntity>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(final BaseEntity<UserEntity> value) {
//                        if (value.getResult() == 1) {
//                            view.loginSuccess(value.getData());
//                        } else {
//                            view.showToast(value.getMsg());
////                            view.loginError(value);
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                       view.loginErrorString(e.toString());
//                        Log.i("asss","Throwable=="+e);
//                        view.dismissProgress();
//                        view.showNetError();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        view.dismissProgress();
//                    }
//                });
                Api.getInstanceGson().login(view.getPhone(), view.getPwd()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(request -> {
                    if (request.getResult()==1) {
                        view.loginSuccess(request.getData());
                    } else {
                        view.showToast(request.getMsg());
                    }
                    view.dismissProgress();
                }, throwable -> {
//                    Log.e(TAG, "error", throwable);
                    view.loginErrorString(throwable.toString());
                    Log.i("asss","Throwable=="+throwable);
                    view.dismissProgress();
                    view.showNetError();
                });
    }
}
