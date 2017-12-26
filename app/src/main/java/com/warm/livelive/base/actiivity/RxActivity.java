package com.warm.livelive.base.actiivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.warm.livelive.LiveApp;
import com.warm.livelive.data.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者: 51hs_android
 * 时间: 2017/8/15
 * 简介:
 */

public abstract class RxActivity extends BaseActivity {

    public DataManager getDataManager() {
        return mDataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    protected DataManager mDataManager;

    /**
     * 管理所有添加进来的Disposable；
     */
    protected CompositeDisposable mCompositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = LiveApp.getInstance().getDataManager();

    }

    protected void addDisposable(Disposable disposable){
        if (mCompositeDisposable==null){
            mCompositeDisposable=new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }
}
