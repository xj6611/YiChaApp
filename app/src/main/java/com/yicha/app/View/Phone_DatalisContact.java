package com.yicha.app.View;


import com.yicha.app.Entity.DatalisEntity;
import com.yicha.app.Entity.PC_DatalisEntity;

import dyc.commlibrary.activity.BaseView;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public interface Phone_DatalisContact {
    interface View extends BaseView {
        int getUid();
        String getID();
        String getToken();
        void DatalisSuccess(PC_DatalisEntity value);
    }

    interface Presenter  {
        void requestDatalis();
    }
}