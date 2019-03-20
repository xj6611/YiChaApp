package com.yicha.app.Utlis;

import com.yicha.app.BaseApplication;

/**
 * Created by wangjia on 2017/3/16.
 */

public class FastDataUtil extends Remember {

    protected static Remember remember = null;
    private static final String SHARED_PREFS_NAME = "tticar.staff";
    private static final String SHOK_REMINDER = "skok::reminder";
    private static final String USER_NAME = "user::name";
    private static final String LOGIN_NAME = "login::name";
    private static final String USER_PASSWORD = "user::password";
    private static final String TOKEN = "user::token";
    private static final String SCOPE = "user::scope";
    private static final String USER_TELEPHONE = "user::telephone";
    private static final String ISLOGIN = "user:isLogin";
    private static final String PASSWORD_REMINDER = "password_reminder";
    private static final String DOWNLOAD_APK_VERSION = "download:apk:version";


    public static synchronized Remember getInstance() {
        if (remember == null) {
            remember = init(BaseApplication.getInstance(), SHARED_PREFS_NAME);
        }
        return remember;
    }

    public static boolean isShokRemind() {
        return getBoolean(SHOK_REMINDER, true);
    }

    public static void setLoginName(String loginName) {
        putString(LOGIN_NAME, loginName);
    }

    public static String getLoginName() {
        return getString(LOGIN_NAME, "");
    }

    public static void setShokReminder(boolean isShokReminder) {
        putBoolean(SHOK_REMINDER, isShokReminder);
    }

    public static String getUserName() {
        return getString(USER_NAME, "");
    }

    public static void setUserName(String userName) {
        putString(USER_NAME, userName);
    }

    public static void setUserPassword(String userPassword) {
        putString(USER_PASSWORD, userPassword);
    }

    public static String getUserPassword() {
        return getString(USER_PASSWORD, "");
    }

    public static boolean getPasswordReminder() {
        return getBoolean(PASSWORD_REMINDER, false);
    }

    public static void setPasswordReminder(boolean isPasswordReminder) {
        putBoolean(PASSWORD_REMINDER, isPasswordReminder);
    }

    public static String getToken() {
        return getString(TOKEN, "");
    }

    public static void setToken(String token) {
        putString(TOKEN, token);
    }

    public static void setScope(String scope) {
        putString(SCOPE, scope);
    }

    public static String getScope() {
        return getString(SCOPE, "");
    }

    public static String getUserTelephone() {
        return getString(USER_TELEPHONE, "");
    }

    public static void setUserTelephone(String userTelephone) {
        putString(USER_TELEPHONE, userTelephone);
    }

    public static void setIsLogin(boolean isLogin) {
        putBoolean(ISLOGIN, isLogin);
    }

    public boolean getIsLogin() {
        return getBoolean(ISLOGIN, false);
    }

    public static void setDownloadApkVersion(String downloadApkVersion) {
        Remember.putString(DOWNLOAD_APK_VERSION, downloadApkVersion);
    }

    public static String getDownloadApkVersion() {
        return Remember.getString(DOWNLOAD_APK_VERSION, "");
    }
}
