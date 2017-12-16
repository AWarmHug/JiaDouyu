package com.warm.baselib.actiivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gloria.pysy.MyApp;
import com.gloria.pysy.base.BasePresenter;
import com.gloria.pysy.di.component.ActivityComponent;
import com.gloria.pysy.di.component.DaggerActivityComponent;
import com.gloria.pysy.di.module.ActivityModule;

import javax.inject.Inject;


/**
 * Created by warm on 17/6/19.
 * 这里的泛型为Presenter
 */

public abstract class BaseMvpActivity< P extends BasePresenter> extends BaseActivity{


    @Inject
    protected P mPresenter;


    public ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(MyApp.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectPresenter(getActivityComponent());
        mPresenter.attach(this);


    }

    protected abstract void injectPresenter(ActivityComponent activityComponent);



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }



}
