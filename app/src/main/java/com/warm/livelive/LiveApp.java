package com.warm.livelive;

import android.app.Application;

import com.warm.livelive.douyu.data.DataManager;
import com.warm.livelive.utils.imageloader.GlideImageLoader;
import com.warm.livelive.utils.imageloader.ImageLoader;

/**
 * 作者：warm
 * 时间：2017-12-25 14:48
 * 描述：
 */

public class LiveApp extends Application {


    private static LiveApp instance;

    private DataManager mDataManager;


    private ImageLoader mImageLoader;





    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mImageLoader = new GlideImageLoader();
        mDataManager = DataManager.getInstance();
    }

    public static LiveApp getInstance() {
        return instance;
    }


    public DataManager getDataManager() {
        return mDataManager;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
