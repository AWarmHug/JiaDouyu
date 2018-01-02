package com.warm.livelive.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.warm.livelive.GlideApp;
import com.warm.livelive.R;
import com.warm.livelive.base.actiivity.BaseMvpActivity;
import com.warm.livelive.config.ApiConfig;
import com.warm.livelive.data.bean.SubChannel;
import com.warm.livelive.mvp.TableContract;
import com.warm.livelive.mvp.TablePresenter;
import com.warm.livelive.ui.livelist.LivePagerAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2017-12-25 17:41
 * 描述：
 */

public class TableActivity extends BaseMvpActivity<TablePresenter> implements TableContract.View, NavigationView.OnNavigationItemSelectedListener {


    private LivePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(tb);
        tb.setNavigationIcon(R.drawable.ic_vec_main);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(nvMenu)) {
                    drawer.closeDrawer(nvMenu);
                } else {
                    drawer.openDrawer(nvMenu);
                }
            }
        });

        GlideApp.with(this).asDrawable()
                .dontAnimate()
                .load(ApiConfig.HEADER_IMG_PAHT)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        nvMenu.getHeaderView(0).setBackground(resource);
                    }
                });
        nvMenu.setNavigationItemSelectedListener(this);
        drawer.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                @TableContract.Tag
                String tag = (String) drawerView.getTag();
                mPresenter.getSubChannel(tag);
            }
        });


        nvMenu.setCheckedItem(R.id.game);
        tb.setTitle("电脑游戏");
        mPresenter.getSubChannel(TableContract.GAME);

    }

    @Override
    protected TablePresenter injectPresenter() {
        return new TablePresenter();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()) {
            return false;
        }

        if (drawer.isDrawerOpen(nvMenu)) {
            drawer.closeDrawer(nvMenu);
        }
        tb.setTitle(item.getTitle());
        switch (item.getItemId()) {
            case R.id.game:
                nvMenu.setTag(TableContract.GAME);
                break;
            case R.id.sjyx:
                nvMenu.setTag(TableContract.SJYX);
                break;
            case R.id.ktyx:
                nvMenu.setTag(TableContract.KTYX);
                break;
            case R.id.kj:
                nvMenu.setTag(TableContract.KJ);
                break;
            case R.id.yz:
                nvMenu.setTag(TableContract.YZ);
                break;
            case R.id.yl:
                nvMenu.setTag(TableContract.YL);
                break;
            case R.id.znl:
                nvMenu.setTag(TableContract.ZNL);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(nvMenu)) {
            drawer.closeDrawer(nvMenu);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void getSubChannel(List<SubChannel> subChannels) {
//        if (mPagerAdapter == null) {
        mPagerAdapter = new LivePagerAdapter(getSupportFragmentManager(), subChannels);
        vpOrder.setAdapter(mPagerAdapter);
        tlTitle.setupWithViewPager(vpOrder);
//        } else {
//            mPagerAdapter.notifyDataSetChanged();
//        }
    }


    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.tl_title)
    TabLayout tlTitle;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;
    @BindView(R.id.nv_menu_left)
    NavigationView nvMenu;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    public int layoutResID() {
        return R.layout.activity_table;
    }


}
