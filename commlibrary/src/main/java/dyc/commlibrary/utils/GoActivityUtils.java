package dyc.commlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 界面跳转工具类
 * Created by dingyc on 2016/11/23.
 */
public class GoActivityUtils {
    /**
     * 跳转界面
     * @param context
     * @param cls
     * @param mBundle
     */
    public static   void startActivity(Context context, Class<?> cls, Bundle mBundle)
    {
        Intent intent=new Intent();
        intent.setClass(context,cls);
        if (mBundle != null) {
            intent.putExtras(mBundle);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转界面
     * @param context
     * @param cls
     * @param mBundle
     */
    public static   void startActivity(Context context, Class<?> cls)
    {
        startActivity(context,cls, null);
    }

    /**
     * 跳转界面关闭当前界面
     * @param context
     * @param cls
     * @param mBundle
     */
    public static   void startActivityKillCurrent(Context context, Class<?> cls,Bundle mBundle)
    {
        Intent intent=new Intent();
        intent.setClass(context,cls);
        if (mBundle != null) {
            intent.putExtras(mBundle);
        }
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    /**
     * 跳转界面关闭当前界面
     * @param context
     * @param cls
     */
    public static   void startActivityKillCurrent(Context context, Class<?> cls)
    {
        startActivityKillCurrent(context, cls,null);
    }

    /**
     * 有返回值的跳转
     * @param cls
     * @param mBundle
     * @param resquestCode
     */
    public static void startActivityForResult(Activity activity, Class<?> cls, Bundle mBundle, int resquestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        if (mBundle != null) {
            intent.putExtras(mBundle);
        }
        activity.startActivityForResult(intent, resquestCode);
    }
}
