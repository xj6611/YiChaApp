package com.yicha.app.View;

import com.yicha.app.Entity.BaseEntity;
import com.yicha.app.Entity.DatalisEntity;

import java.util.List;
import java.util.Map;

import dyc.commlibrary.activity.BaseView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by CYY on 2018/12/8.
 */

public interface UploadHeaderContact {
    interface View extends BaseView {
        Map<String, RequestBody> getParts();
        List<MultipartBody.Part> getSoundRecordFile();
        void Success(BaseEntity value);
    }

    interface Presenter  {
        void request();
    }
}
