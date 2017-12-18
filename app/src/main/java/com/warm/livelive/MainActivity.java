package com.warm.livelive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.warm.playerlib.controller.JustBasePlayController;
import com.warm.playerlib.weight.JustVideoView;

public class MainActivity extends AppCompatActivity {
    public static final String LIVE_URL = "http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4";
    //    public static final String LIVE_URL="http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";
    private JustVideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (JustVideoView) findViewById(R.id.videoView);
        JustBasePlayController controller=new JustBasePlayController(this);
        videoView.addController(controller);
        videoView.setDataSource(LIVE_URL);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
