package com.warm.baselib;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：warm
 * 时间：2017-07-26 17:59
 * 描述：
 */

public class RxPresenter<V extends BaseView> implements BasePresenter<V> {
    protected V mView;



    /**
     * 管理所有添加进来的Disposable；
     */
    protected CompositeDisposable mCompositeDisposable;


    protected void addDisposable(Disposable disposable){
        if (mCompositeDisposable==null){
            mCompositeDisposable=new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void attach(V view) {
        this.mView=view;
    }



    @Override
    public void detach() {
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }

    }
    public V getmView() {
        return mView;
    }

    public boolean isEmpty(@NonNull CharSequence str){
        return TextUtils.isEmpty(str);

    }

}
