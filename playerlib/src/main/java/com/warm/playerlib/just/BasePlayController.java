package com.warm.playerlib.just;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * 作者：warm
 * 时间：2017-12-18 10:02
 * 描述：
 */

public abstract class BasePlayController extends RelativeLayout {


    private PlayControl mControl;

    private GestureDetector mGesture;

    private int seekSpeed = 25;

    /**
     * 是否全屏
     */
    private boolean fulling;

    /**
     * 控制器是否显示
     */
    private boolean controllerShow = true;


    public void setControl(PlayControl playControl) {
        this.mControl = playControl;
    }

    public BasePlayController(Context context) {
        super(context);
    }

    public BasePlayController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasePlayController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, getLayoutRes(), this);
        mGesture = new GestureDetector(getContext(), new MyGester());
    }

    protected abstract int getLayoutRes();


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mGesture.onTouchEvent(ev);//手势添加按压的事件
        return true;
    }


    public void start() {
        mControl.start();
    }

    public void pause() {
        mControl.pause();
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

        mControl.setScaleType(scale);
    }

    public long getDuration() {
        return mControl.getDuration();
    }

    public boolean isPlaying() {
        return mControl.isPlaying();
    }

    public void seekTo(long seekTo) {
        mControl.seekTo(seekTo);
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
                mControl.seekTo((int) (mControl.getCurrentPosition() - distanceX * seekSpeed));

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

    }

}
