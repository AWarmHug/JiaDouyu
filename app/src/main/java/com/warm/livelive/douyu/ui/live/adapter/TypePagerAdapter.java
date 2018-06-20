package com.warm.livelive.douyu.ui.live.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.warm.livelive.douyu.data.bean.live.LiveCate1;
import com.warm.livelive.douyu.ui.live.TypePagerItemFragment;

/**
 * 作者：warm
 * 时间：2018-06-12 12:43
 * 描述：
 */
public class TypePagerAdapter extends FragmentStatePagerAdapter {
    private LiveCate1 mLiveCate1;
    private int mSize;
    public static final int ITEM_COUNT = 12;

    public TypePagerAdapter(FragmentManager fm, @NonNull LiveCate1 liveCate1) {
        super(fm);
        this.mLiveCate1 = liveCate1;
        mSize = liveCate1.getCate2_count() / ITEM_COUNT;
        if (liveCate1.getCate2_count() % ITEM_COUNT != 0) {
            mSize += 1;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return TypePagerItemFragment.newInstance(mLiveCate1, position);
    }

    @Override
    public int getCount() {
        return mSize;
    }
}
