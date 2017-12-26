package com.warm.livelive.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;

import com.warm.livelive.R;
import com.warm.livelive.base.IBasePresenter;


/**
 * Created by warm on 17/6/19.
 */

public abstract class BaseMvpFragment<P extends IBasePresenter> extends BaseFragment {

    protected P mPresenter;

    public abstract P initPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter=initPresenter();
        mPresenter.attach(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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




    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

}
