package com.warm.livelive.douyu.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;

import com.warm.livelive.R;
import com.warm.livelive.base.actiivity.BaseActivity;
import com.warm.livelive.douyu.data.bean.RtmpUrl;
import com.warm.livelive.douyu.data.bean.live.LiveRoomItem;
import com.warm.livelive.douyu.mvp.PlayContract;
import com.warm.livelive.douyu.mvp.PlayPresenter;
import com.warm.playerlib.custom.live.LivePlayController;
import com.warm.playerlib.player.BasePlayController;
import com.warm.playerlib.player.JustVideoPlayer;

import java.util.Map;

import butterknife.BindView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;

public class PlayActivity extends BaseActivity implements PlayContract.View {
    public static final String LIVE_URL = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
    //    public static final String LIVE_URL="http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";
    private static final String KEY_LIVE_ROOM_ITEM = "LiveRoomItem";

    private PlayPresenter mPresenter;

    @BindView(R.id.videoView)
    JustVideoPlayer videoView;

    private LiveRoomItem item;

    private DanmakuContext mDanmaContext;

    private BasePlayController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = getIntent().getParcelableExtra(KEY_LIVE_ROOM_ITEM);

        mPresenter = new PlayPresenter();
        mPresenter.attach(this);


        // 设置最大显示行数
        Map<Integer, Integer> maxLinesPair = new ArrayMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示5行
        // 设置是否禁止重叠
        Map<Integer, Boolean> overlappingEnablePair = new ArrayMap<>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        mDanmaContext = DanmakuContext.create();
        mDanmaContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f).setScaleTextSize(1.2f)
//                .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//        .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair).setDanmakuMargin(40);
        if (item!=null&&item.getIs_vertical() == 0) {

            mController = new LivePlayController(this);
        }else {
            // TODO: 2018/6/20 添加一个竖直的播放器
            mController = new LivePlayController(this);
        }
        mPresenter.playPrepare(item);
//        mPresenter.playPrepare(String.valueOf(rtmpUrl.getRoom_id()), "-9999");
    }


    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }


    @Override
    public void onBackPressed() {
        if (videoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        videoView.release();
        mPresenter.detach();
        super.onDestroy();

    }


    private BaseDanmaku createDanmaku(boolean islive, String txt) {
        BaseDanmaku danmaku = mDanmaContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);

        danmaku.text = txt;
        danmaku.padding = 5;
        danmaku.priority = 0;
        danmaku.isLive = islive;
        danmaku.textSize = 32;
        danmaku.textColor = Color.WHITE;
        return danmaku;

    }

    public static Intent goPlay(Context context, LiveRoomItem item) {
        Intent intent = new Intent();
        intent.setClass(context, PlayActivity.class);
        intent.putExtra(KEY_LIVE_ROOM_ITEM, item);
        return intent;
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_player;
    }

    @Override
    public void playPrepared(RtmpUrl rtmpUrl) {
        videoView.setDataSource(rtmpUrl.getRtmp_url() + "/" + rtmpUrl.getRtmp_live())
//                .setAutoRotation()
                .setDanma(mDanmaContext, new BaseDanmakuParser() {
                    @Override
                    protected IDanmakus parse() {
                        return new Danmakus();
                    }
                })
                .addController(mController);
    }

    @Override
    public void showDanmu(String type, String msg) {
        videoView.addDanma(createDanmaku(true,msg));
    }
}
