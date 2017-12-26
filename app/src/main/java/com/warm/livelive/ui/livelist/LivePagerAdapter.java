package com.warm.livelive.ui.livelist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.warm.livelive.data.bean.SubChannel;

import java.util.List;

/**
 * 作者：warm
 * 时间：2017-12-26 09:12
 * 描述：
 */

public class LivePagerAdapter extends FragmentStatePagerAdapter {
    private List<SubChannel> mSubChannels;

    public LivePagerAdapter(FragmentManager fm, List<SubChannel> subChannels) {
        super(fm);
        this.mSubChannels=subChannels;
    }

    @Override
    public Fragment getItem(int position) {
        return LiveRoomFragment.newInstance(mSubChannels.get(position));
    }

    @Override
    public int getCount() {
        return mSubChannels.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mSubChannels.get(position).getTag_name();
    }
}
