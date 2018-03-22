package com.warm.livelive.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.warm.livelive.R;
import com.warm.livelive.base.actiivity.BaseMvpActivity;
import com.warm.livelive.data.bean.RtmpUrl;
import com.warm.livelive.mvp.PlayerContract;
import com.warm.livelive.mvp.PlayerPresenter;
import com.warm.playerlib.custom.JustBasePlayController;
import com.warm.playerlib.just.JustVideoPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;

public class PlayerActivity extends BaseMvpActivity<PlayerPresenter> implements PlayerContract.View {
    public static final String LIVE_URL = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
    //    public static final String LIVE_URL="http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";

    public static final String NAME_PLAY_URL = "PLAY_URL";
    private JustVideoPlayer videoView;
    private Button button;
    private List<Integer> scaleType;
    private int i;
    private RtmpUrl rtmpUrl;

    private DanmakuContext mDanmaContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        videoView = (JustVideoPlayer) findViewById(R.id.videoView);
        button = (Button) findViewById(R.id.button);

        scaleType = new ArrayList<>();
        scaleType.add(JustVideoPlayer.SCALE_WRAP_CONTENT);
        scaleType.add(JustVideoPlayer.SCALE_MATCH_PARENT);
        scaleType.add(JustVideoPlayer.SCALE_4_3);
        scaleType.add(JustVideoPlayer.SCALE_16_9);

        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示5行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        mDanmaContext = DanmakuContext.create();
        mDanmaContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f).setScaleTextSize(1.2f)
//                .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//        .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair).setDanmakuMargin(40);


        final JustBasePlayController controller = new JustBasePlayController(this);

        videoView.setDataSource(rtmpUrl.getRtmp_url()+"/"+rtmpUrl.getRtmp_live())
//                .setAutoRotation()
                .setDanma(mDanmaContext, new BaseDanmakuParser() {
                    @Override
                    protected IDanmakus parse() {
                        return new Danmakus();
                    }
                })
                .addController(controller);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                controller.setScaleType(scaleType.get(i));
                i++;
                if (i == 4) {
                    i = 0;
                }
                String danma="弹幕"+i;
                videoView.addDanma(createDanmaku(true,danma));
            }
        });

        mPresenter.loadPrepare(String.valueOf(rtmpUrl.getRoom_id()), "-9999");
    }


    @Override
    protected PlayerPresenter injectPresenter() {
        return new PlayerPresenter();
    }

    private void initData() {
        rtmpUrl = getIntent().getParcelableExtra(NAME_PLAY_URL);
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
        super.onDestroy();
        videoView.release();

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

    @Override
    public int layoutResID() {
        return R.layout.activity_player;
    }

    @Override
    public void getDanmu(String danmu) {
        if (videoView.isPlaying()){
            videoView.addDanma(createDanmaku(true,danmu));
        }
    }
}
