package com.warm.livelive.base.actiivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.warm.livelive.base.IBasePresenter;


/**
 * Created by warm on 17/6/19.
 * 这里的泛型为Presenter
 */

public abstract class BaseMvpActivity<P extends IBasePresenter> extends BaseActivity {


    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = injectPresenter();
        mPresenter.attach(this);
    }

    protected abstract P injectPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }


}
