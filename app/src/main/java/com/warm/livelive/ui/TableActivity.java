package com.warm.livelive.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.warm.livelive.R;
import com.warm.livelive.base.actiivity.RxActivity;
import com.warm.livelive.data.bean.SubChannel;
import com.warm.livelive.ui.livelist.LivePagerAdapter;
import com.warm.livelive.utils.rx.CustomObserver;
import com.warm.livelive.utils.rx.RxUtils;

import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;

/**
 * 作者：warm
 * 时间：2017-12-25 17:41
 * 描述：
 */

public class TableActivity extends RxActivity {

    public static final String NAME = "game";

    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.tl_title)
    TabLayout tlTitle;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(tb);

        mDataManager.getSubChannel(NAME)
                .compose(RxUtils.<List<SubChannel>>ioToMain(this))
                .subscribe(new CustomObserver<List<SubChannel>>(mCompositeDisposable, this) {
                    @Override
                    public void onNext(@NonNull List<SubChannel> subChannels) {
                        showList(subChannels);
                    }
                });
    }

    private void showList(List<SubChannel> subChannels) {

        LivePagerAdapter livePagerAdapter = new LivePagerAdapter(getSupportFragmentManager(),subChannels);
        vpOrder.setAdapter(livePagerAdapter);
        tlTitle.setupWithViewPager(vpOrder);

    }

    @Override
    public int layoutResID() {
        return R.layout.activity_table;
    }
}
