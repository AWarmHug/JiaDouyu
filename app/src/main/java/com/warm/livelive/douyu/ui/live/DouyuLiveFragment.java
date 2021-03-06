package com.warm.livelive.douyu.ui.live;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.LazyFragment;
import com.warm.livelive.douyu.data.bean.KeyWord;
import com.warm.livelive.douyu.data.bean.TabCate1;
import com.warm.livelive.douyu.mvp.DouyuLiveContract;
import com.warm.livelive.douyu.mvp.DouyuLivePresenter;
import com.warm.livelive.douyu.ui.search.SearchActivity;
import com.warm.tablayout.TabLayout;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-11 12:28
 * 描述：
 */
public class DouyuLiveFragment extends LazyFragment implements DouyuLiveContract.View {

    @BindView(R.id.edit)
    TextView tvSearch;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.pager)
    ViewPager mPager;

    private DouyuLivePresenter mPresenter;

    private Adapter mAdapter;

    public static DouyuLiveFragment newInstance() {
        Bundle args = new Bundle();
        DouyuLiveFragment fragment = new DouyuLiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DouyuLivePresenter();
        mPresenter.attach(this);
    }

    @Override
    protected void doInVisible() {

    }

    @Override
    protected void doFirstVisible() {

        if (mAdapter == null) {
            mPresenter.getTabCate1List();
            mPresenter.getTodayHot();
        }
        tvSearch.setOnClickListener(v -> startActivity(new Intent(getContext(), SearchActivity.class)));
    }

    @Override
    protected void doVisible() {

    }

    @Override
    public void getTabCate1List(List<TabCate1> tabCate1s) {
        mAdapter = new DouyuLiveFragment.Adapter(getChildFragmentManager(), tabCate1s);
        mPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mPager);
    }

    @Override
    public void showTodayHot(KeyWord keyWords) {
        tvSearch.setHint(keyWords.getKw());
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

    @Override
    public int layoutResId() {
        return R.layout.fragment_douyu_live;
    }

}
