package com.yicha.app.View;

import com.yicha.app.Entity.PC_DatalisEntity;

import dyc.commlibrary.activity.BaseView;

public interface Card_DatalisContact {
    interface Presenter {
        void requestDatalis();
    }

    interface View extends BaseView {
        void DatalisSuccess(PC_DatalisEntity paramPC_DatalisEntity);

        String getID();

        String getToken();

        int getUid();
    }
}
