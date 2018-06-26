package com.warm.livelive.douyu.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.warm.livelive.R;
import com.warm.livelive.base.fragment.BaseFragment;
import com.warm.tablayout.TabLayout;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-22 10:14
 * 描述：
 */
public class SearchResultFragment extends BaseFragment implements SearchResultAllFragment.OnSelectActionListener {
    public static final String KEY_WORD = "kw";

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.pager)
    ViewPager mPager;

    private String kw;


    public static SearchResultFragment newInstance(String kw) {

        Bundle args = new Bundle();
        args.putString(KEY_WORD, kw);
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SearchResultFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            kw = bundle.getString(KEY_WORD);
        }
        mPager.setAdapter(new Adapter(getChildFragmentManager()));
        mTab.setupWithViewPager(mPager);
    }

    @Override
    public void onSelect(int position) {
        mPager.setCurrentItem(position,false);
    }


    class Adapter extends FragmentStatePagerAdapter {
        private String[] titles = {"全部", "直播", "视频", "主播"};

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    SearchResultAllFragment allFragment = SearchResultAllFragment.newInstance(kw);
                    allFragment.setOnSelectActionListener(SearchResultFragment.this);
                    return allFragment;
                case 1:
                    SearchResultLiveFragment liveFragment = SearchResultLiveFragment.newInstance(kw);
                    return liveFragment;
                case 2:
                    SearchResultVideoFragment videoFragment = SearchResultVideoFragment.newInstance(kw);
                    return videoFragment;
                case 3:
                    SearchResultAnchorFragment anchorFragment = SearchResultAnchorFragment.newInstance(kw);
                    return anchorFragment;
                default:
                    SearchResultAllFragment allFragment1 = SearchResultAllFragment.newInstance(kw);
                    return allFragment1;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


    @Override
    public int layoutResId() {
        return R.layout.fragment_search_result;
    }


}
