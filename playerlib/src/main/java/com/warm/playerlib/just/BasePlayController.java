package com.warm.playerlib.just;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 作者：warm
 * 时间：2017-12-18 10:02
 * 描述：
 */

public abstract class BasePlayController extends FrameLayout {


    private PlayControl player; //Player

    private GestureDetector mGesture;

    private int seekSpeed = 25;

    protected View content;


    /**
     * 控制器是否显示
     */
    private boolean controllerShow = true;


    public void setControl(PlayControl playControl) {
        this.player = playControl;
    }

    public BasePlayController(Context context) {
        this(context, null);
    }

    public BasePlayController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasePlayController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        content = LayoutInflater.from(context).inflate(getLayoutRes(), this);
        mGesture = new GestureDetector(getContext(), new MyGester());
    }

    protected abstract int getLayoutRes();


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mGesture.onTouchEvent(ev);//手势添加按压的事件
        return true;
    }

    private Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            if (isPlaying()) {
                onCurrentChange(player.getCurrentPosition());
            }
            postDelayed(progressRunnable, 1000);
        }
    };


    public final void start() {
        player.start();
        post(progressRunnable);

    }


    public final void pause() {
        player.pause();
        removeCallbacks(progressRunnable);

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

        player.setScaleType(scale);
    }

    public void onCurrentChange(long current) {


    }

    public void startAgain() {
        player.startAgain();
    }


    /**
     * 当前播放View的状态
     * //用于设置各种状态的ui。
     *
     * @param state {@link JustVideoView#STATE_COMPLETION}等等
     */
    public abstract void setPlayState(int state);


    public final long getDuration() {
        return player.getDuration();
    }


    public final boolean isPlaying() {
        return player.isPlaying();
    }

    public final void seekTo(long seekTo) {
//        player.pause();
        player.seekTo(seekTo);
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
                System.out.print("****************" + distanceX);
                //让videoView的播放位置移动到手势拖动后的位置(*15知识为了缩小滑动比例)
                player.seekTo((int) (player.getCurrentPosition() - distanceX * seekSpeed));

            } else {

            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }


    public interface PlayControl {
        boolean isPlaying();

        void start();

        void pause();

        void seekTo(long seekTo);

        void toFull();

        void toNotFull();

        long getCurrentPosition();

        long getDuration();

        void setScaleType(int scaleType);

        void startAgain();
    }


}
