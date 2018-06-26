package com.warm.livelive.base.actiivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.warm.livelive.MyApp;
import com.warm.livelive.douyu.data.http.HttpManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者: 51hs_android
 * 时间: 2017/8/15
 * 简介:
 */

public abstract class RxActivity extends BaseActivity {

    public HttpManager getDataManager() {
        return mHttpManager;
    }

    public void setDataManager(HttpManager httpManager) {
        this.mHttpManager = httpManager;
    }

    protected HttpManager mHttpManager;

    /**
     * 管理所有添加进来的Disposable；
     */
    protected CompositeDisposable mCompositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHttpManager = MyApp.getInstance().getHttpManager();

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
