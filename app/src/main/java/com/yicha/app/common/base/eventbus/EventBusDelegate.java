package com.yicha.app.common.base.eventbus;

import org.greenrobot.eventbus.EventBus;

public class EventBusDelegate {

    private static final String TAG = "EventBusDelegate";

    public static void register(Object context) {
        try {
            EventBus.getDefault().register(context);
        } catch (Throwable e) {
          /*  if (BuildConfig.DEBUG) {
                Log.d(TAG, "error", e);
            }*/
            // ignore
        }
    }

    public static void unregister(Object context) {
        try {
            EventBus.getDefault().unregister(context);
        } catch (Throwable e) {
            /*if (BuildConfig.DEBUG) {
                Log.d(TAG, "error", e);
            }*/
            // ignore
        }
    }
}
