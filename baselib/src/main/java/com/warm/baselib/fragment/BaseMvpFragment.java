package com.warm.baselib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

import com.gloria.pysy.MyApp;
import com.gloria.pysy.R;
import com.gloria.pysy.base.BasePresenter;
import com.gloria.pysy.di.component.DaggerFragmentComponent;
import com.gloria.pysy.di.component.FragmentComponent;
import com.gloria.pysy.di.module.FragmentModule;

import javax.inject.Inject;


/**
 * Created by warm on 17/6/19.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    @Inject
    protected P mPresenter;



    public FragmentComponent getComponent() {
        return DaggerFragmentComponent.builder().appComponent(MyApp.getAppComponent()).fragmentModule(new FragmentModule(this)).build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        injectPresenter(getComponent());
        mPresenter.attach(this);
        super.onViewCreated(view, savedInstanceState);
        setTouchBack(view);
    }


    public void setTouchBack(View view) {
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


    protected abstract void injectPresenter(FragmentComponent component);


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

}
