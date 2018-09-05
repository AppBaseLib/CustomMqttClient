package com.abt.basic;

import android.app.Application;

/**
 * @描述： @基类application
 * @作者： @黄卫旗
 * @创建时间： @05/09/2018
 */
public abstract class BasicApplication extends Application {

    private static BasicApplication sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        init();
        initComplete();
    }

    public static final BasicApplication getAppContext() {
        return sContext;
    }

    private final void init() {
        if (BuildConfig.DEBUG) {
            //DebugManage.initialize(this);
        }
    }

    public abstract void initComplete();
}
