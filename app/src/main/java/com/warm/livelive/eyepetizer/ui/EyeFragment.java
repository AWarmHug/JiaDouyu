package com.warm.livelive.eyepetizer.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.LazyFragment;
import com.warm.tablayout.TabLayout;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-17 02:52
 * 描述：
 */
public class EyeFragment extends LazyFragment {
    private static final String TAG = "EyeFragment";
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;

    public static EyeFragment newInstance() {

        Bundle args = new Bundle();

        EyeFragment fragment = new EyeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void doFirstVisible() {


    }

    @Override
    protected void doVisible() {


    }

    @Override
    protected void doInVisible() {

    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_eye;
    }
}
