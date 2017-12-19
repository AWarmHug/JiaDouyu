package com.warm.playerlib.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.warm.playerlib.R;
import com.warm.playerlib.just.BasePlayController;
import com.warm.playerlib.just.JustVideoView;

/**
 * 作者：warm
 * 时间：2017-12-18 14:41
 * 描述：
 */

public class JustBasePlayController extends BasePlayController implements BottomBar.OnBottomOperationListener {
    private static final String TAG = "JustBasePlayController";
    private TitleBar title;
    private BottomBar bottom;
    private ProgressBar progressBar;
    private ImageView startAgain;
    private Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            animTitleBottom();

        }
    };

    public JustBasePlayController(Context context) {
        this(context, null);
    }

    public JustBasePlayController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustBasePlayController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        title = (TitleBar) findViewById(R.id.title);
        bottom = (BottomBar) findViewById(R.id.bottom);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        startAgain = (ImageView) findViewById(R.id.start_again);

        bottom.setOnBottomOperationListener(this);
        startAgain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startAgain.setVisibility(GONE);
                startAgain();
            }
        });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_play;
    }

    @Override
    public void show() {
        animTitleBottom();
    }

    @Override
    public void dismiss() {
        animTitleBottom();
    }

    @Override
    public void setPlayState(int state) {
        Log.d(TAG, "setPlayState: state=" + state);
        switch (state) {
            case JustVideoView.STATE_PREPARING:
            case JustVideoView.STATE_BUFFERING_START:
                progressBar.setVisibility(VISIBLE);
                break;
            case JustVideoView.STATE_PREPARED:
            case JustVideoView.STATE_BUFFERING_END:
                progressBar.setVisibility(GONE);
                break;
            case JustVideoView.STATE_ERROR:
                break;
            case JustVideoView.STATE_PAUSE:
                break;
            case JustVideoView.STATE_PLAYING:
                progressBar.setVisibility(GONE);
                animTitleBottom();
                break;
            case JustVideoView.STATE_COMPLETION:
                startAgain.setVisibility(VISIBLE);
                break;

        }

    }

    private void animTitleBottom() {
        if (title.getTranslationY() == 0) {
            title.animate().translationY(-title.getHeight()).setDuration(500).start();
            bottom.animate().translationY(bottom.getHeight()).setDuration(500).start();
            removeCallbacks(mRunnable);
        } else {
            title.animate().translationY(0).setDuration(500).start();
            bottom.animate().translationY(0).setDuration(500).start();
            postDelayed(mRunnable, 5000);

        }
    }

    @Override
    public void setBuffering(int percent) {
//        super.setBuffering(percent);
        bottom.setBuffering(percent);

    }

    @Override
    public void seekBarTo(int progress) {

        seekTo(progress > 99 ? 99 * getHundredth() : progress * getHundredth());
    }

    private long getHundredth() {
        return getDuration() / 100;
    }


    @Override
    public void play(boolean play) {
        if (play) {
            start();
        } else {
            pause();
        }
    }

    @Override
    public void onCurrentChange(long current) {
//        super.onCurrentChange(current);
        Log.d(TAG, "onCurrentChange: current=" + current);
        if (getDuration() != 0)
            bottom.updateProgress(current, (int) (current * 100 / getDuration()));
    }

    @Override
    public void toFull(boolean full) {

    }


}
