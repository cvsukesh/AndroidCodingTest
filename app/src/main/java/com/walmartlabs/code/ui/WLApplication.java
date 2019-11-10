package com.walmartlabs.code.ui;

import android.app.Application;
import android.content.Context;

public class WLApplication extends Application {

    private static Context appContext;

    private static WLApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        appContext = this;
    }

    public static WLApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }
}
