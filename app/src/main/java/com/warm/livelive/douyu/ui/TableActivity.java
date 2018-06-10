package com.warm.livelive.douyu.ui;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.warm.livelive.GlideApp;
import com.warm.livelive.R;
import com.warm.livelive.base.actiivity.BaseMvpActivity;
import com.warm.livelive.douyu.config.DouyuConfig;
import com.warm.livelive.douyu.data.bean.SubChannel;
import com.warm.livelive.douyu.data.bean.TabCate;
import com.warm.livelive.douyu.ui.livelist.LivePagerAdapter;
import com.warm.livelive.douyu.mvp.TableContract;
import com.warm.livelive.douyu.mvp.TablePresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：warm
 * 时间：2017-12-25 17:41
 * 描述：
 */

public class TableActivity extends BaseMvpActivity<TablePresenter> implements TableContract.View {


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
                .load(DouyuConfig.HEADER_IMG_PAHT)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        nvMenu.getHeaderView(0).findViewById(R.id.header).setBackground(resource);
                    }
                });
        drawer.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        mPresenter.getTabCate();


    }

    @Override
    protected TablePresenter injectPresenter() {
        return new TablePresenter();
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
    public void getTabCate(final List<TabCate> tabCates) {

        RadioGroup group = (RadioGroup) nvMenu.getHeaderView(0).findViewById(R.id.ll_items);
        for (int i = 0; i < tabCates.size(); i++) {
            if (tabCates.get(i).getLevel() == 1) {
                RadioButton rb = new RadioButton(this);
                rb.setId(i);
                rb.setText(tabCates.get(i).getCate_name());
                rb.setButtonDrawable(new ColorDrawable(ContextCompat.getColor(this,R.color.transparent)));
                rb.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.navi_item_checked));
                RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                rb.setLayoutParams(lp);
                group.addView(rb);
            }
        }


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (drawer.isDrawerOpen(nvMenu)) {
                    drawer.closeDrawer(nvMenu);
                }

                mPresenter.getSubChannel(tabCates.get(checkedId).getShort_name());
            }
        });
        group.check(0);

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
