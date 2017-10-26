package com.example.pengshan.litepaldemo;

import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by pengshan on 2017/9/13.
 */

public class BaseApplication extends LitePalApplication {
    private static BaseApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Context getAppContext() {
        return mInstance;
    }
}
