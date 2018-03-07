package com.warm.playerlib.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.warm.playerlib.R;
import com.warm.playerlib.just.BasePlayController;
import com.warm.playerlib.just.JustVideoPlayer;

/**
 * 作者：warm
 * 时间：2017-12-18 14:41
 * 描述：
 */

public class JustBasePlayController extends BasePlayController implements BottomBar.OnBottomOperationListener {
    private static final String TAG = "JustBasePlayController";
    private TitleBar title;
    private BottomBar bottom;
    private ProgressBar progressBar, progressBarDismiss;
    private ImageView startAgain;
    private Runnable mRunnable = new Runnable() {
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
        progressBarDismiss = (ProgressBar) findViewById(R.id.progressbar_dismiss);
        startAgain = (ImageView) findViewById(R.id.start_again);

        bottom.setOnBottomOperationListener(this);
        startAgain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startAgain.setVisibility(GONE);
                startAgain();
            }
        });
        bottom.initValue();
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
            case JustVideoPlayer.STATE_PREPARING:
                progressBar.setVisibility(VISIBLE);
                break;
            case JustVideoPlayer.STATE_PREPARED:
                progressBar.setVisibility(GONE);
                bottom.setDuration(getDuration());
                break;
            case JustVideoPlayer.STATE_ERROR:
                break;
            case JustVideoPlayer.STATE_PAUSE:
                removeCallbacks(progressRunnable);
                break;
            case JustVideoPlayer.STATE_PLAYING:
//                post(progressRunnable);
                setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                progressBar.setVisibility(GONE);
                animTitleBottom();
                break;
            case JustVideoPlayer.STATE_COMPLETION:
                startAgain.setVisibility(VISIBLE);
                break;
        }
        bottom.setPlayState(state == JustVideoPlayer.STATE_PLAYING);
    }

    @Override
    public void onBufferState(int state) {
        switch (state){
            case JustVideoPlayer.BUFFERING_START:
                progressBar.setVisibility(VISIBLE);
                break;
            case JustVideoPlayer.BUFFERING_END:
                progressBar.setVisibility(GONE);
                break;
        }

    }

    private void animTitleBottom() {
        if (title.getTranslationY() == 0) {
            title.animate().translationY(-title.getHeight()).setDuration(300).start();
            bottom.animate().translationY(bottom.getHeight()).setDuration(300).start();
            progressBarDismiss.setVisibility(VISIBLE);
            progressBarDismiss.animate().alpha(1f).setDuration(300).start();
            removeCallbacks(mRunnable);
        } else {
            title.animate().translationY(0).setDuration(300).start();
            bottom.animate().translationY(0).setDuration(300).start();
            progressBarDismiss.setVisibility(GONE);
            postDelayed(mRunnable, 5000);
        }
    }

    @Override
    public void onPlayerStateChange(int state) {

        if (state == STATE_FULL) {
            setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        bottom.setFullState(state == STATE_FULL);
    }

    @Override
    public void setBuffering(int percent) {
//        super.setBuffering(percent);
        bottom.setBuffering(percent);
        progressBarDismiss.setSecondaryProgress(percent);
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
            pauseUser();
        }
    }

    @Override
    public void onCurrentChange(long current) {
//        super.onCurrentChange(current);
        if (getDuration() != 0) {
            if (title.getTranslationY() == 0) {
                bottom.updateProgress(current, (int) (current * 100 / getDuration()));
            }
            if (progressBarDismiss.isShown()) {
                progressBarDismiss.setProgress((int) (current * 100 / getDuration()));
            }
        }

    }


    @Override
    public void toFull(boolean full) {
        if (full) {
            toFull();
        } else {
            toNotFull();
        }
    }


}
