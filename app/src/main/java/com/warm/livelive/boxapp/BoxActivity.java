package com.warm.livelive.boxapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.warm.livelive.R;
import com.warm.livelive.base.actiivity.BaseActivity;
import com.warm.livelive.douyu.ui.DouyuFragment;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2018-06-08 16:24
 * 描述：
 */
public class BoxActivity extends BaseActivity implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPager.setAdapter(new Adapter(getSupportFragmentManager()));
        mPager.addOnPageChangeListener(this);
        mBottom.setOnNavigationItemSelectedListener(this);
    }

    @BindView(R.id.pager_content)
    ViewPager mPager;
    @BindView(R.id.bottom)
    BottomNavigationView mBottom;

    @Override
    public int layoutResID() {
        return R.layout.activity_box;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_douyu:
                mPager.setCurrentItem(0, false);
                break;
            case R.id.menu_bilibili:
                mPager.setCurrentItem(1, false);
                break;
            case R.id.menu_huya:
                mPager.setCurrentItem(2, false);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int ids[] = {R.id.menu_douyu, R.id.menu_bilibili, R.id.menu_huya};
        mBottom.setSelectedItemId(ids[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class Adapter extends FragmentStatePagerAdapter {
        String[] title = {"斗鱼", "斗鱼", "斗鱼"};

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DouyuFragment.newInstance();
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
