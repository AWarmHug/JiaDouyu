package com.warm.livelive.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：warm
 * 时间：2017-07-26 17:59
 * 描述：
 */

public abstract class RxPresenter<V> implements BasePresenter<V> {
    /**
     * 管理所有添加进来的Disposable；
     */
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }


    @Override
    public void detach() {
        mCompositeDisposable.clear();
    }


}
