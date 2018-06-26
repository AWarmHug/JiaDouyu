package com.warm.livelive;

import android.app.Application;

import com.warm.livelive.douyu.data.dao.DaoManager;
import com.warm.livelive.douyu.data.http.HttpManager;
import com.warm.livelive.utils.imageloader.GlideImageLoader;
import com.warm.livelive.utils.imageloader.ImageLoader;

/**
 * 作者：warm
 * 时间：2017-12-25 14:48
 * 描述：
 */

public class MyApp extends Application {


    private static MyApp instance;

    private HttpManager mHttpManager;
    private DaoManager mDaoManager;

    private ImageLoader mImageLoader;





    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mImageLoader = new GlideImageLoader();
        mHttpManager = HttpManager.getInstance();
        mDaoManager=DaoManager.getInstance();

    }

    public static MyApp getInstance() {
        return instance;
    }

    public HttpManager getHttpManager() {
        return mHttpManager;
    }

    public DaoManager getDaoManager() {
        return mDaoManager;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
