package com.yicha.app.View;

import com.yicha.app.Entity.BaseEntity;

import dyc.commlibrary.activity.BaseView;

/**
 * Created by CYY on 2018/12/8.
 */

public interface ResetYourPasswordContant {
    interface View extends BaseView {
        int getID();
        String getToken();
        String getPwd();
        String getYuanPwd();
        void Success(BaseEntity value);
    }

    interface Presenter  {
        void request();
    }

}
