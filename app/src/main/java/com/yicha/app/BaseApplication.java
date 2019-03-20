package com.yicha.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;


import com.hss01248.dialog.MyActyManager;
import com.hss01248.dialog.StyledDialog;
import com.yicha.app.Entity.UserEntity;
import com.yicha.app.Utlis.AppUtil;
import com.yicha.app.Utlis.FastDataUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dyc.commlibrary.utils.SPUtils;

/**
 * Created by 刘贵 on 2017/6/9.
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    // 声明AMapLocationClient类对象
    public static Context mContext;
    public static Typeface typeFace;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;
        //数据存储注册
        FastDataUtil.getInstance();
        initDialog();
    }
    private void initDialog() {
        StyledDialog.init(this);
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                //在这里保存顶层activity的引用(内部以软引用实现)
                MyActyManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public void saveRecordSearch(String keyWord) {
        List<String> list = SPUtils.getObjFromSp(this, AppUtil.KEY_RECORD_LIST);
        if (list == null) {
            list = new ArrayList<>();
            list.add(keyWord);
        } else {
            if (!list.contains(keyWord)) {
                list.add(keyWord);
            }
        }

        SPUtils.saveObj2SP(this, list, AppUtil.KEY_RECORD_LIST);
    }

    public void clearRecord() {
        SPUtils.saveObj2SP(this, null, AppUtil.KEY_RECORD_LIST);
    }


    public List<String> getRecordSearchList() {
        List<String> list = SPUtils.getObjFromSp(this, AppUtil.KEY_RECORD_LIST);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public void clearUserInfo() {
        SPUtils.saveObj2SP(this, null, AppUtil.KEY_USER);
    }

    public UserEntity getUserInfo() {
        UserEntity userEntity = SPUtils.getObjFromSp(this, AppUtil.KEY_USER);
        return userEntity;
    }

    public void saveUserInfo(UserEntity userEntity) {
        SPUtils.saveObj2SP(this, userEntity, AppUtil.KEY_USER);
    }

    public int getUserId() {
        UserEntity userEntity = getUserInfo();
        if (userEntity != null) {
            return userEntity.getId();
        } else {
            return 0;
        }
    }
    public String getUserFace() {
        UserEntity userEntity = getUserInfo();
        if (userEntity != null) {
            return userEntity.getHeader();
        } else {
            return "";
        }
    }
//     public String getIdcard() {
//        UserEntity userEntity = getUserInfo();
//        if (userEntity != null) {
//            return userEntity.getIdcard();
//        } else {
//            return "";
//        }
//    }
//
//    public int getUserPoint() {
//        UserEntity userEntity = getUserInfo();
//        if (userEntity != null) {
//            return userEntity.getPoint();
//        } else {
//            return 0;
//        }
//    }
    public String getUserName() {
        UserEntity userEntity = getUserInfo();
        if (userEntity != null) {
            return userEntity.getName();
        } else {
            return "";
        }
    }
     public String getNumber() {
        UserEntity userEntity = getUserInfo();
        if (userEntity != null) {
            return userEntity.getNumber();
        } else {
            return "";
        }
    }

    public String getPwd() {
        UserEntity userEntity = getUserInfo();
        if (userEntity != null) {
            return userEntity.getPwd();
        } else {
            return "";
        }
    }

    public String getToken() {
        UserEntity userEntity = getUserInfo();
        if (userEntity != null) {
            return userEntity.getToken();
        } else {
            return "";
        }
    }

    public String getPayPwd() {
        SharedPreferences sp = getSharedPreferences("info2", MODE_PRIVATE);

        //获得保存在SharedPredPreferences中的用户名和密码
        String password = sp.getString("pwd", "");

        return password;
    }
    public static String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(mContext);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
