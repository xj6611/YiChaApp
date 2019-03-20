package com.yicha.app.View;


import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.UserEntity;

import dyc.commlibrary.activity.BaseView;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public interface LoginContact {
    interface View extends BaseView {
        String getPhone();
        String getPwd();
        void loginSuccess(UserEntity value);
        void loginError(BaseEntity value);
        void loginErrorString(String value);
    }

    interface Presenter  {
        void requestLogin();
    }
}