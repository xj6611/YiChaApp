package com.yicha.app.Presenter;

import android.util.Log;

import com.yicha.app.Entity.BannerEntity;
import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.PC_DatalisEntity;
import com.yicha.app.RetrofitServiceApi.RetrofitClient;
import com.yicha.app.RetrofitServiceApi.RetrofitService;
import com.yicha.app.View.BannerContact;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BannerPresenter implements BannerContact.Presenter {
    BannerContact.View view;

    public BannerPresenter(BannerContact.View paramView) {
        this.view = paramView;
    }

    public void request() {
        this.view.showProgress(null);
        (RetrofitClient.getInstance().create(RetrofitService.class)).getBannerList(this.view.getUid(), this.view.getToken())
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseEntity<List<BannerEntity>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final BaseEntity<List<BannerEntity>> value) {
                if (value.getResult() == 1) {
                    view.Success(value.getData());
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
