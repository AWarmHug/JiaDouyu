package com.warm.livelive.base;


import com.warm.livelive.LiveApp;
import com.warm.livelive.data.DataManager;

/**
 * 作者：warm
 * 时间：2017-12-09 09:54
 * 描述：
 */

public class BasePresenter<V extends BaseView> implements IBasePresenter<V> {
    protected DataManager mDataManager;

    protected V mView;


    @Override
    public void attach(V view) {
        this.mView = view;
        mDataManager = LiveApp.getInstance().getDataManager();
    }

    @Override
    public void detach() {
        this.mView = null;
    }

    public V getView() {
        return mView;
    }
}
