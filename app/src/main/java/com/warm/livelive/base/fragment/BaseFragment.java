package com.warm.livelive.base.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.warm.livelive.R;
import com.warm.livelive.base.BaseView;
import com.warm.livelive.base.actiivity.BaseActivity;
import com.warm.livelive.error.CustomException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者: 51hs_android
 * 时间: 2017/3/10
 * 简介: Fragment的基类，其他Fragment不要直接继承, ViewPager上的fragment继承{@link LazyFragment} 其他fragment继承RxFragment
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    private Unbinder binder;

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
    }


    private ProgressDialog getPDialog() {
        return ((BaseActivity) getActivity()).getPDialog();
    }

    @Override
    public void onShowLoad() {
        if (getActivity() instanceof BaseActivity) {

            if (!getPDialog().isShowing()) {
                getPDialog().show();
            }

        }
    }

    @Override
    public void onDismissLoad() {
        if (getPDialog().isShowing()) {
            getPDialog().dismiss();
        }
    }

    @Override
    public void onTakeException(@NonNull final CustomException error) {

        Snackbar.make(getView() == null ? getActivity().getWindow().getDecorView() : getView()
                , error.getMessage()
                , Snackbar.LENGTH_LONG)
                .setAction(error.getActionName() == null ? getString(R.string.i_know) : error.getActionName()
                        , new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (error.getListener() != null) {
                                    error.getListener().errorAction();
                                }
                            }
                        })
                .show();

    }




    @Override
    public Context getBVContext() {
        return getContext();
    }

    @Override
    public BaseActivity getBVActivity() {
        if (getActivity() instanceof BaseActivity) {
            return (BaseActivity) getActivity();
        } else {
            throw new RuntimeException("fragment 需要放到BaseActivity上");
        }
    }

    /**
     * @return statuBar高度
     */
    public int getStateBarHeight() {

        return ((BaseActivity) getActivity()).getStateBarHeight();
    }

    public void onBackPressed() {
        getActivity().onBackPressed();
    }

    @ColorInt
    public int getColor(@ColorRes int colorRes) {
        return ContextCompat.getColor(getContext(), colorRes);
    }

    public boolean isEmpty(@Nullable CharSequence str) {
        return TextUtils.isEmpty(str);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }

    public abstract int layoutResId();


}
