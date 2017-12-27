package com.warm.livelive.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.warm.livelive.R;
import com.warm.livelive.base.actiivity.BaseMvpActivity;
import com.warm.livelive.data.bean.HlsUrl;
import com.warm.livelive.mvp.PlayerContract;
import com.warm.livelive.mvp.PlayerPresenter;
import com.warm.playerlib.custom.JustBasePlayController;
import com.warm.playerlib.just.JustVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends BaseMvpActivity<PlayerPresenter> implements PlayerContract.View {
    public static final String LIVE_URL = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
    //    public static final String LIVE_URL="http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";

    public static final String NAME_PLAY_URL = "PLAY_URL";
    private JustVideoPlayer videoView;
    private Button button;
    private List<Integer> scaleType;
    private int i;
    private HlsUrl hlsUrl;

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


        final JustBasePlayController controller = new JustBasePlayController(this);
        videoView.setDataSource(hlsUrl.getHls_url())
//                .setAutoRotation()
                .addController(controller);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.setScaleType(scaleType.get(i));
                i++;
                if (i == 4) {
                    i = 0;
                }
            }
        });
        mPresenter.loadPrepare(hlsUrl.getRoomId(),"-9999");

    }

    @Override
    protected PlayerPresenter injectPresenter() {
        return new PlayerPresenter();
    }

    private void initData() {
        hlsUrl = getIntent().getParcelableExtra(NAME_PLAY_URL);
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

    @Override
    public int layoutResID() {
        return R.layout.activity_player;
    }
}
