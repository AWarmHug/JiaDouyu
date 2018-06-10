package com.warm.playerlib.custom.live;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.warm.playerlib.R;
import com.warm.playerlib.player.BasePlayController;
import com.warm.playerlib.player.JustVideoPlayer;

/**
 * 作者：warm
 * 时间：2018-05-15 17:41
 * 描述：
 */
public class LivePlayController extends BasePlayController implements View.OnClickListener {
    private static final String TAG = "LivePlayController";

    private TextView tvTitle;
    private ImageButton btLock;
    private ProgressBar mpBar;
    private ImageButton btToFull;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            animTitleBottom();
        }
    };

    public LivePlayController(Context context) {
        this(context, null);
    }

    public LivePlayController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LivePlayController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tvTitle = findViewById(R.id.tv_title);
        btLock = findViewById(R.id.bt_lock);
        mpBar = findViewById(R.id.progressBar);
        btToFull = findViewById(R.id.bt_toFull);

        tvTitle.setOnClickListener(this);
        btLock.setOnClickListener(this);
        btToFull.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.controller_play_live;
    }

    @Override
    protected boolean isPostProgressRunnable() {
        return false;
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
                mpBar.setVisibility(VISIBLE);
                break;
            case JustVideoPlayer.STATE_PREPARED:
                mpBar.setVisibility(GONE);
                break;
            case JustVideoPlayer.STATE_ERROR:
                break;
            case JustVideoPlayer.STATE_PAUSE:
                break;
            case JustVideoPlayer.STATE_PLAYING:
                setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                mpBar.setVisibility(GONE);
                animTitleBottom();
                break;
            case JustVideoPlayer.STATE_COMPLETION:

                break;
        }
    }


    private void animTitleBottom() {
        if (tvTitle.getTranslationY() == 0) {
            tvTitle.animate().translationY(-tvTitle.getHeight()).setDuration(300).start();
            btLock.animate().translationX(-btLock.getWidth()).setDuration(300).start();
            btToFull.animate().translationX(btLock.getWidth()).setDuration(300).start();

            removeCallbacks(mRunnable);
        } else {
            tvTitle.animate().translationY(0).setDuration(300).start();
            btLock.animate().translationX(0).setDuration(300).start();
            btToFull.animate().translationX(0).setDuration(300).start();
            postDelayed(mRunnable, 5000);
        }
    }




    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_toFull) {
            switchFull();
        }else if (i==R.id.bt_lock){
            Toast.makeText(getContext(), "暂未实现", Toast.LENGTH_SHORT).show();
        }else if (i==R.id.tv_title){
            if (getContext() instanceof Activity){
                ((Activity) getContext()).onBackPressed();
            }
        }
    }
}
