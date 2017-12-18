package com.warm.playerlib.controller;

import android.content.Context;
import android.util.AttributeSet;

import com.warm.playerlib.R;
import com.warm.playerlib.weight.PlayBottomBar;
import com.warm.playerlib.weight.TitleBar;

/**
 * 作者：warm
 * 时间：2017-12-18 14:41
 * 描述：
 */

public class JustBasePlayController extends BasePlayController implements PlayBottomBar.OnBottomOperationListener {
    private TitleBar title;
    private PlayBottomBar bottom;


    public JustBasePlayController(Context context) {
        this(context, null);
    }

    public JustBasePlayController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustBasePlayController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        title = (TitleBar) findViewById(R.id.title);
        bottom = (PlayBottomBar) findViewById(R.id.bottom);
        bottom.setOnBottomOperationListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_play;
    }

    @Override
    public void show() {
        animController();
    }

    @Override
    public void dismiss() {
        animController();
    }

    private void animController() {
        if (title.getTranslationY() == 0) {
            title.animate().translationY(-title.getHeight()).setDuration(500).start();
            bottom.animate().translationY(bottom.getHeight()).setDuration(500).start();
//            mHandler.removeMessages(MSG_CONTROLLER);
        } else {
            title.animate().translationY(0).setDuration(500).start();
            bottom.animate().translationY(0).setDuration(500).start();
//            mHandler.sendEmptyMessageDelayed(MSG_CONTROLLER,5000);
        }
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
    public void toFull(boolean full) {

    }

    @Override
    public void updateProgress(int m, int progress) {

    }
}
