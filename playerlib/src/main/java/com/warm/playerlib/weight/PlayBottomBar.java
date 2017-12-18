package com.warm.playerlib.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.warm.playerlib.R;

import java.util.Formatter;
import java.util.Locale;


/**
 * Created by warm on 17/5/6.
 */

public class PlayBottomBar extends RelativeLayout implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {


    /**
     * 播放状态，用于标记播放情况
     */
    private boolean playing = false;

    /**
     * 屏幕的状态
     */
    private boolean fulling = false;

    private ImageButton bt_play, bt_full;
    private TextView tv_time;
    private SeekBar sb_progress;

    private OnBottomOperationListener onBottomOperationListener;

    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;

    public void setOnBottomOperationListener(OnBottomOperationListener onBottomOperationListener) {
        this.onBottomOperationListener = onBottomOperationListener;
    }

    public PlayBottomBar(Context context) {
        this(context, null);
    }

    public PlayBottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayBottomBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {

        inflate(context, R.layout.bar_bottom, this);

        bt_play = (ImageButton) findViewById(R.id.bt_play);
        bt_play.setOnClickListener(this);
        tv_time = (TextView) findViewById(R.id.tv_time);
        sb_progress = (SeekBar) findViewById(R.id.sb_progress);
        sb_progress.setOnSeekBarChangeListener(this);
        sb_progress.setSecondaryProgress(0);
        bt_full = (ImageButton) findViewById(R.id.bt_full);
        bt_full.setOnClickListener(this);
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
    }


    private String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.d("seek", "onProgressChanged: " + progress);
        Log.d("seek", "onProgressChanged: " + seekBar.getSecondaryProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (onBottomOperationListener != null)
            onBottomOperationListener.seekBarTo(seekBar.getProgress());

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_play) {
            switchPlay(playing);

        } else if (i == R.id.bt_full) {
            switchFull(fulling);
        }
    }

    public void switchPlay(boolean isPlaying) {
        if (isPlaying) {
            playing = false;
            if (onBottomOperationListener != null)
                onBottomOperationListener.play(false);
            bt_play.setImageResource(R.drawable.ic_vec_play_small);
        } else {
            playing = true;
            if (onBottomOperationListener != null)
                onBottomOperationListener.play(true);
            bt_play.setImageResource(R.drawable.ic_vec_play_stop_small);
        }
    }


    public void switchFull(boolean isfulling) {

        if (isfulling) {
            fulling = false;
            if (onBottomOperationListener != null)
                onBottomOperationListener.toFull(false);
            bt_full.setImageResource(R.drawable.ic_vec_play_nofull);
        } else {
            fulling = true;
            if (onBottomOperationListener != null)
                onBottomOperationListener.toFull(true);
            bt_full.setImageResource(R.drawable.ic_vec_play_tofull);
        }

    }

    public void updataProgress(int m, int progress) {
        updataProgress(m, progress, 0);
        if (onBottomOperationListener != null)
            onBottomOperationListener.updateProgress(m, progress);

    }


    public void updataProgress(int m, int progress, int secondProgress) {
        Log.d("EnVideoPlayer", "updateProgress: " + progress + "," + secondProgress);

        tv_time.setText(stringForTime(m));

        sb_progress.setProgress(progress);
//        sb_progress.setSecondaryProgress(secondProgress);
    }

    public interface OnBottomOperationListener {
        void seekBarTo(int progress);

        void play(boolean play);

        void toFull(boolean full);

        void updateProgress(int m, int progress);
    }


}
