package com.yicha.app.common.base;

import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.Disposable;

public interface BasePresenterView {

    AppCompatActivity getCurrentActivity();

    void addDisposable(Disposable disposable);
}
