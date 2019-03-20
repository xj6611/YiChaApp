package com.yicha.app.Presenter;

import android.util.Log;

import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.PC_DatalisEntity;
import com.yicha.app.RetrofitServiceApi.RetrofitClient;
import com.yicha.app.RetrofitServiceApi.RetrofitService;
import com.yicha.app.View.UploadHeaderContact;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CYY on 2018/12/8.
 */

public class UploadHeaderPresenter implements UploadHeaderContact.Presenter{
    UploadHeaderContact.View view;

    public UploadHeaderPresenter(UploadHeaderContact.View view) {
        this.view = view;
    }

    @Override
    public void request() {
        view.showProgress(null);
        RetrofitService service = RetrofitClient.getInstance().create(RetrofitService.class);
        service.uploadHeader(view.getParts(),view.getSoundRecordFile())//获取Observable对象
                .subscribeOn(Schedulers.newThread())//请求在新的线程中执行
                .observeOn(Schedulers.io())         //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())//最后在主线程中执行
                .subscribe(new Observer<BaseEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final BaseEntity value) {
                        if (value.getResult() == 1) {
                            view.Success(value);
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
