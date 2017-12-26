package com.warm.livelive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.warm.playerlib.custom.JustBasePlayController;
import com.warm.playerlib.just.JustVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String LIVE_URL = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
    //    public static final String LIVE_URL="http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";
    private JustVideoPlayer videoView;
    private Button button;
    private List<Integer> scaleType;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (JustVideoPlayer) findViewById(R.id.videoView);
        button = (Button) findViewById(R.id.button);

        scaleType = new ArrayList<>();
        scaleType.add(JustVideoPlayer.SCALE_WRAP_CONTENT);
        scaleType.add(JustVideoPlayer.SCALE_MATCH_PARENT);
        scaleType.add(JustVideoPlayer.SCALE_4_3);
        scaleType.add(JustVideoPlayer.SCALE_16_9);

        final JustBasePlayController controller = new JustBasePlayController(this);
        videoView.setDataSource(LIVE_URL)
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
}
