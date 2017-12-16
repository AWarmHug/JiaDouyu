package com.warm.baselib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

import com.gloria.pysy.MyApp;
import com.gloria.pysy.R;
import com.gloria.pysy.data.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者: 51hs_android
 * 时间: 2017/8/12
 * 简介:
 */

public abstract class RxFragment extends BaseFragment {

    protected DataManager mDataManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = MyApp.getAppComponent().getDataManager();
    }

    /**
     * 给底view添加了点击事件防止触发其他点击事件
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTouchBack(view);
    }



    public void setTouchBack(View view){
        if (view.getBackground()==null){
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white_bg));
        }
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

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
    public void onDestroyView() {
        super.onDestroyView();
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }
}
