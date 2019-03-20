package com.yicha.app.View;

import com.yicha.app.Entity.PC_GuJiaEntity;

import java.util.Map;

import dyc.commlibrary.activity.BaseView;
import okhttp3.RequestBody;

public interface GuJiaContact {
    interface Presenter {
        void request();

        void requestCard();

        void requestMonbile();
    }

     interface View extends BaseView {
        void Success(PC_GuJiaEntity paramPC_GuJiaEntity);

        Map<String, RequestBody> getParts();
    }
}
