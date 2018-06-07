package com.warm.playerlib.custom.video;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.warm.playerlib.R;

import java.util.Formatter;
import java.util.Locale;


/**
 * 作者：warm
 * 时间：2017-12-19 08:56
 * 描述：
 */

public class BottomBar extends FrameLayout implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {


    /**
     * 播放状态，用于标记播放情况
     */
    private boolean mPlaying = false;

    /**
     * 屏幕的状态
     */
    private boolean mFulling = false;

    private ImageButton bt_play, bt_full;
    private TextView tv_cur, tv_dur;
    private SeekBar sb_progress;

    private OnBottomOperationListener onBottomOperationListener;

    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;

    public void setOnBottomOperationListener(OnBottomOperationListener onBottomOperationListener) {
        this.onBottomOperationListener = onBottomOperationListener;
    }

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {

        inflate(context, R.layout.bar_bottom, this);

        bt_play = (ImageButton) findViewById(R.id.bt_play);
        bt_play.setOnClickListener(this);
        tv_cur = (TextView) findViewById(R.id.tv_cur);
        tv_dur = (TextView) findViewById(R.id.tv_dur);
        sb_progress = (SeekBar) findViewById(R.id.sb_progress);
        sb_progress.setOnSeekBarChangeListener(this);
        sb_progress.setSecondaryProgress(0);
        bt_full = (ImageButton) findViewById(R.id.bt_full);
        bt_full.setOnClickListener(this);
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
    }

    /**
     * dismiss只显示一个seekbar
     */
    public void dismiss() {

    }

    /**
     * 其他东西都显示出来
     */
    public void show() {


    }


    public void setDuration(long duration) {
        tv_dur.setText(stringForTime(duration));
    }

    public String stringForTime(long timeMs) {
        long totalSeconds = timeMs / 1000;

        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;

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

    public void initValue() {
        updateProgress(0, 0);
        setDuration(0);
    }

    public void setFullState(boolean fulling) {
        this.mFulling = fulling;
    }

    public void setPlayState(boolean playing) {
        if (this.mPlaying != playing) {
            this.mPlaying = playing;
            bt_play.setImageResource(playing ? R.drawable.ic_vec_pause : R.drawable.ic_vec_start);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_play) {
            switchPlay();

        } else if (i == R.id.bt_full) {
            switchFull();
        }
    }

    public void switchPlay() {
        if (onBottomOperationListener != null) {
            onBottomOperationListener.play(!mPlaying);
        }
    }


    public void switchFull() {
        if (onBottomOperationListener != null)
            onBottomOperationListener.toFull(!mFulling);
    }


    public void updateProgress(long current, int progress) {

        tv_cur.setText(stringForTime(current));

        sb_progress.setProgress(progress);

    }

    public void setBuffering(int percent) {
        sb_progress.setSecondaryProgress(percent);

    }


    public interface OnBottomOperationListener {
        void seekBarTo(int progress);

        void play(boolean play);

        void toFull(boolean full);
    }


}
