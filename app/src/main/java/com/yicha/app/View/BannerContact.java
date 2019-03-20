package com.yicha.app.View;

import com.yicha.app.Entity.BannerEntity;

import java.util.List;

import dyc.commlibrary.activity.BaseView;

public interface BannerContact {
    interface Presenter {
        void request();
    }

    interface View extends BaseView {
        void Error();

        void Success(List<BannerEntity> paramList);

        String getToken();

        int getUid();
    }
}
