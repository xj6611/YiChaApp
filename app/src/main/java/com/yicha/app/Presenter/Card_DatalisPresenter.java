package com.yicha.app.Presenter;

import android.util.Log;

import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.PC_DatalisEntity;
import com.yicha.app.RetrofitServiceApi.RetrofitClient;
import com.yicha.app.RetrofitServiceApi.RetrofitService;
import com.yicha.app.View.Card_DatalisContact;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Card_DatalisPresenter implements Card_DatalisContact.Presenter {
    Card_DatalisContact.View view;

    public Card_DatalisPresenter(Card_DatalisContact.View view) {
        this.view = view;
    }

    @Override
    public void requestDatalis() {
        view.showProgress(null);
        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
        service.getBikeOptions(view.getUid(),view.getToken(),view.getID())//获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<BaseEntity<PC_DatalisEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BaseEntity<PC_DatalisEntity> value) {
                        if (value.getResult() == 1) {
                            view.DatalisSuccess(value.getData());
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
                        view.dismissProgress();
                    }
                });
    }
}
