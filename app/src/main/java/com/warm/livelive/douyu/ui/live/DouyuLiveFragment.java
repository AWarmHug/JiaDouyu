package com.warm.livelive.douyu.ui.live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.LazyRxFragment;
import com.warm.livelive.douyu.data.bean.TabCate1;
import com.warm.livelive.utils.rx.ThrowableConsumer;
import com.warm.tablayout.TabLayout;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：warm
 * 时间：2018-06-11 12:28
 * 描述：
 */
public class DouyuLiveFragment extends LazyRxFragment {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.pager)
    ViewPager mPager;

    private Adapter mAdapter;

    public static DouyuLiveFragment newInstance() {
        Bundle args = new Bundle();
        DouyuLiveFragment fragment = new DouyuLiveFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void doInVisible() {

    }

    @Override
    protected void doFirstVisible() {
        if (mAdapter == null) {
            Disposable d = mDataManager.getTabCate1List()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(tabCate1s -> {
                        tabCate1s.add(0, TabCate1.TYPE);
                        tabCate1s.add(1, TabCate1.ALL);
                        tabCate1s.add(tabCate1s.size(), TabCate1.SPORT);
                        return tabCate1s;
                    })
                    .subscribe(tabCate1s -> {
                        mAdapter = new Adapter(getChildFragmentManager(), tabCate1s);
                        mPager.setAdapter(mAdapter);
                        mTab.setupWithViewPager(mPager);
                    }, new ThrowableConsumer(this));
            addDisposable(d);
        }
    }

    @Override
    protected void doVisible() {

    }

    @Override
    public int layoutResId() {
        return R.layout.fragment_douyu_live;
    }


    class Adapter extends FragmentStatePagerAdapter {
        List<TabCate1> tabCate1s;

        Adapter(FragmentManager fm, List<TabCate1> tabCate1s) {
            super(fm);
            this.tabCate1s = tabCate1s;

        }

        @Override
        public Fragment getItem(int position) {
            TabCate1 tabCate1 = tabCate1s.get(position);
            if (tabCate1 == TabCate1.TYPE) {
                return LiveTypeFragment.newInstance();
            } else if (tabCate1 == TabCate1.ALL) {
                return LiveListFragment.newInstance(tabCate1.getLevel(), tabCate1.getCate_id());
            } else if (tabCate1 == TabCate1.SPORT) {
                return LiveListFragment.newInstance(tabCate1.getLevel(), tabCate1.getCate_id());
            } else {
                return LiveTabItemFragment.newInstance(tabCate1);
            }
        }

        @Override
        public int getCount() {
            return tabCate1s.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabCate1s.get(position).getCate_name();
        }
    }

}
