package com.warm.baselib.actiivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gloria.pysy.MyApp;
import com.gloria.pysy.data.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者: 51hs_android
 * 时间: 2017/8/15
 * 简介:
 */

public abstract class RxActivity extends BaseActivity {

    /**
     * 管理所有添加进来的Disposable；
     */
    protected CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = MyApp.getAppComponent().getDataManager();

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
