package com.warm.livelive;

import android.app.Application;

import com.warm.livelive.data.DataManager;

/**
 * 作者：warm
 * 时间：2017-12-25 14:48
 * 描述：
 */

public class LiveApp extends Application {

    private static LiveApp instance;

    private DataManager mDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mDataManager = DataManager.newInstance();

    }

    public static LiveApp getInstance() {
        return instance;
    }


    public DataManager getDataManager() {
        return mDataManager;
    }
}
