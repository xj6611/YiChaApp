package com.yicha.app.View;

import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.DatalisEntity;

import dyc.commlibrary.activity.BaseView;

/**
 * Created by CYY on 2018/12/8.
 */

public interface ForgotPasswordContant {
    interface View extends BaseView {
        String getPhone();
        String getPwd();
        int getCode();
        void DatalisSuccess(BaseEntity value);
        void CodeSuccess(BaseEntity value);
    }

    interface Presenter  {
        void request();
        void requestCode();
    }

}
