package com.warm.livelive.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.warm.livelive.base.BaseView;
import com.warm.livelive.base.actiivity.BaseActivity;
import com.warm.livelive.error.KnownException;
import com.warm.livelive.utils.CheckUtil;
import com.warm.livelive.widget.load.DouyuLoadView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者: 51hs_android
 * 时间: 2017/3/10
 * 简介: Fragment的基类，其他Fragment不要直接继承, ViewPager上的fragment继承{@link LazyRxFragment} 其他fragment继承RxFragment
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    private Unbinder binder;
    private ViewGroup mViewGroup;
    private DouyuLoadView mLoadView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binder = ButterKnife.bind(this, view);
        if (view instanceof ViewGroup){
            mViewGroup= (ViewGroup) view;
            mLoadView=new DouyuLoadView(view.getContext());
            mViewGroup.addView(mLoadView);
            mLoadView.setVisibility(View.GONE);
        }
    }


    @Override
    public void emptyLoad() {
        CheckUtil.checkNotNull(mViewGroup);

    }

    @Override
    public void dismissEmptyLoad() {

    }


    @Override
    public void toast(@NonNull final KnownException error) {
        if (getBVActivity() != null) {
            getBVActivity().toast(error);
        }
    }

    public void toast(@NonNull final String error) {
        if (getBVActivity() != null) {
            getBVActivity().toast(error);
        }
    }


    @Override
    public Context getBVContext() {
        return getContext();
    }

    @Nullable
    @Override
    public BaseActivity getBVActivity() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            return (BaseActivity) getActivity();
        } else {
            return null;
        }
    }

    /**
     * @return statuBar高度
     */
    public int getStateBarHeight() {

        if (getBVActivity() != null) {
            return getBVActivity().getStateBarHeight();
        }else {
            return 0;
        }

    }

    public void onBackPressed() {
        if (getBVActivity() != null) {
            getBVActivity().onBackPressed();
        }
    }

    @ColorInt
    public int getColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(getContext(), colorRes);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }

    public abstract int layoutResId();


}
