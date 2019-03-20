package com.yicha.app.View;


import com.yicha.app.Entity.HoemPageEntity;
import com.yicha.app.Entity.MobleListEntity;
import com.yicha.app.Entity.UserEntity;
import com.yicha.app.Entity.getMobleListEntity;

import java.util.List;

import dyc.commlibrary.activity.BaseView;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public interface HoemPageContact {
    interface View extends BaseView {
        int getUid();
        String getToken();
        String getModel();
        int getPage();
        int getPageSize();
        int getPage2();
        int getPageSize2();

        void HomePageSuccess(List<MobleListEntity> value);
        void refreshList(List<getMobleListEntity> list);
        void loadMoreList(List<getMobleListEntity> list);
        void showOrHideLoadMore(boolean show);
        void refreshList2(List<MobleListEntity> list);
        void loadMoreList2(List<MobleListEntity> list);
        void showOrHideLoadMore2(boolean show);
        void loadDataSuccess();
        void loadDataSuccess2();
        void Error();
    }

    interface Presenter  {
        void requestHoemPage();
        void requestXingHaoHoemPage(boolean b);
        void requestPCHoemPage();
        void requestCarHoemPage();
        void requestPCXingHaoHoemPage(boolean b);
        void requestCarXingHaoHoemPage(boolean b);
    }
}