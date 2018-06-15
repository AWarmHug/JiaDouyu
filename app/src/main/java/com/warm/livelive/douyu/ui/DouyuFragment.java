package com.warm.livelive.douyu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.LazyRxFragment;
import com.warm.livelive.douyu.ui.live.DouyuLiveFragment;
import com.warm.tablayout.TabLayout;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-11 09:45
 * 描述：
 */
public class DouyuFragment extends LazyRxFragment {


    @BindView(R.id.tb)
    Toolbar mTb;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.pager)
    ViewPager mPager;

    private Adapter mAdapter;


    public static DouyuFragment newInstance() {

        Bundle args = new Bundle();

        DouyuFragment fragment = new DouyuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void doInVisible() {

    }

    @Override
    protected void doFirstVisible() {
        mAdapter=new Adapter(getChildFragmentManager());
        mPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mPager);
    }

    @Override
    protected void doVisible() {

    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_douyu;
    }

    class Adapter extends FragmentStatePagerAdapter {
        String[] title = {"直播", "视频", "发现"};

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           return DouyuLiveFragment.newInstance();
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

}
