package com.warm.playerlib.player;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * 作者：warm
 * 时间：2017-12-18 10:02
 * 描述：Controller的基类,封装了各种操作子类直接调用
 */
public abstract class BasePlayController extends FrameLayout {


    private PlayControl mPlayer; //Player

    private GestureDetector mGesture;

    private int seekSpeed = 25;

    public static final int STATE_NO_FULL = 0;
    public static final int STATE_FULL = 1;

    private int mPlayerState = STATE_NO_FULL;

    public int getPlayerState() {
        return mPlayerState;
    }

    /**
     * 控制器是否显示
     */
    private boolean controllerShow = true;


    public void setControl(PlayControl playControl) {
        this.mPlayer = playControl;
    }

    public BasePlayController(Context context) {
        this(context, null);
    }

    public BasePlayController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasePlayController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, getLayoutRes(), this);
        mGesture = new GestureDetector(getContext(), new MyGester());
    }

    protected abstract int getLayoutRes();

    protected abstract boolean isPostProgressRunnable();

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mGesture.onTouchEvent(ev);//手势添加按压的事件
        return true;
    }

    protected Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            if (isPlaying()) {
                onCurrentChange(mPlayer.getCurrentPosition());
                postDelayed(progressRunnable, 1000);
            }
        }
    };


    public final void start() {
        mPlayer.start();
        if (isPostProgressRunnable()) {
            post(progressRunnable);
        }
    }


    public final void pauseUser() {
        mPlayer.pauseUser();
        if (isPostProgressRunnable()) {
            removeCallbacks(progressRunnable);
        }
    }

    /**
     * 显示出来
     */
    public void show() {

    }

    /**
     * 隐藏掉
     */
    public void dismiss() {

    }


    public void setScaleType(int scale) {

        mPlayer.setScaleType(scale);
    }

    public void onCurrentChange(long current) {

    }

    public void startAgain() {
        mPlayer.replay();
    }


    void setPlayerState(int state) {
        mPlayerState = state;
        onPlayerStateChange(state);
    }

    public void onPlayerStateChange(int state) {

    }

    public void toFull() {
        mPlayer.toFull();
    }

    public void toNotFull() {
        mPlayer.toNotFull();
    }

    public void switchFull() {
        if (isFull()) {
            toNotFull();
        } else {
            toFull();
        }
    }

    /**
     * 当前播放View的状态
     * //用于设置各种状态的ui。
     *
     * @param state {@link JustVideoPlayer#STATE_COMPLETION}等等
     */
    public abstract void setPlayState(int state);

    public void onBufferState(int state) {

    }


    public final long getDuration() {
        return mPlayer.getDuration();
    }


    public final boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    public final void seekTo(long seekTo) {
//        player.pauseUser();
        mPlayer.seekTo(seekTo);
    }

    public boolean isFull() {
        return mPlayer.isFull();
    }

    public void setBuffering(int percent) {

    }

    /**
     * 定义手势监听的内部类继承 GestureDetector.SimpleOnGestureListener
     * 重写onScroll滑动监听的方法，左右滑动值监听X轴
     */
    private class MyGester extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (controllerShow) {
                dismiss();
            } else {
                show();
            }
            controllerShow = !controllerShow;
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (Math.abs(distanceX) > Math.abs(distanceY)) {//横向移动距离大于纵向，只监听横向
                if (distanceX > 0) {
                    //说明是往左滑动
                } else if (distanceX < 0) {

                }
                Log.d("****************", "onScroll: " + distanceX);
                //让videoView的播放位置移动到手势拖动后的位置(*15知识为了缩小滑动比例)
            } else {

            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(progressRunnable);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            post(progressRunnable);
        }
    }


}
