package com.yicha.app.View;

import com.yicha.app.Entity.QueryLog_Entity;

import java.util.List;

import dyc.commlibrary.activity.BaseView;

public interface QueryLogContact {
    interface Presenter {
        void requestDatalis();
    }

    interface View extends BaseView {
        int getPage();

        int getPageSize();

        String getToken();

        int getUid();

        void loadDataSuccess();

        void loadMoreList(List<QueryLog_Entity> paramList);

        void refreshList(List<QueryLog_Entity> paramList);

        void showOrHideLoadMore(boolean paramBoolean);
    }
}
