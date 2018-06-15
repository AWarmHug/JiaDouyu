package com.warm.livelive.widget.recycleview2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者: 51hs_android
 * 时间: 2017/4/11
 * 简介: 加载更多的RecycleView
 */

public class LoadRecyclerView extends RecyclerView {
    private static final String TAG = "EnRecycleView";
    //当滑动加载，最后的位置
    public static final int BOTTOM_SIZE = 2;

    private OnLoadMoreListener onLoadMoreListener;

    private float startY;
    private float startX;
    // 记录viewPager是否拖拽的标记
    private boolean mIsVpDragger;

    /**
     * 是否加载结束，在UI界面控制，
     */
    private boolean loadMoreAble;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;


    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public LoadRecyclerView(Context context) {
        this(context, null);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public void setLoadMoreAble(boolean loadMoreAble) {
        this.loadMoreAble = loadMoreAble;
    }

    public boolean isLoadMoreAble() {
        return loadMoreAble;
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        if (layout instanceof LinearLayoutManager) {
            linearLayoutManager = (LinearLayoutManager) layout;
        }
        if (layout instanceof GridLayoutManager) {
            gridLayoutManager = (GridLayoutManager) layout;
        }
    }

    /**
     * 打开默认局部刷新动画
     */
    public void openDefaultAnimator() {
        this.getItemAnimator().setAddDuration(120);
        this.getItemAnimator().setChangeDuration(250);
        this.getItemAnimator().setMoveDuration(250);
        this.getItemAnimator().setRemoveDuration(120);
        ((SimpleItemAnimator) this.getItemAnimator()).setSupportsChangeAnimations(true);
    }

    /**
     * 关闭默认局部刷新动画
     */
    public void closeDefaultAnimator() {
        this.getItemAnimator().setAddDuration(0);
        this.getItemAnimator().setChangeDuration(0);
        this.getItemAnimator().setMoveDuration(0);
        this.getItemAnimator().setRemoveDuration(0);
        ((SimpleItemAnimator) this.getItemAnimator()).setSupportsChangeAnimations(false);
        setItemAnimator(null);
    }


    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (linearLayoutManager != null || gridLayoutManager != null) {
            int lastVisibleItem = 0;
            int totalItemCount = 0;
            if (linearLayoutManager != null) {
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                totalItemCount = linearLayoutManager.getItemCount();
            }
            if (gridLayoutManager != null) {
                lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                totalItemCount = gridLayoutManager.getItemCount();
            }

            if (onLoadMoreListener != null && lastVisibleItem >= totalItemCount - BOTTOM_SIZE && loadMoreAble) {
                onLoadMoreListener.onLoadMore();
                loadMoreAble = false;
            }
        }

    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
//        switch (state){
//            case RecyclerView.SCROLL_STATE_IDLE:
//                LiveApp.getInstance().getImageLoader().resume(getContext());
//                break;
//            case RecyclerView.SCROLL_STATE_DRAGGING:
//                LiveApp.getInstance().getImageLoader().pause(getContext());
//                break;
//            case RecyclerView.SCROLL_STATE_SETTLING:
//                LiveApp.getInstance().getImageLoader().pause(getContext());
//                break;
//        }

    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }


    //    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 记录手指按下的位置
                startY = ev.getY();
                startX = ev.getX();
                // 初始化标记
//                mIsVpDragger = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果横向的RecycleView正在拖拽中，那么不拦截它的事件，直接return false；
//                if (mIsVpDragger) {
//                    return false;
//                }
                // 获取当前手指位置
                float endY = ev.getY();
                float endX = ev.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                // 如果X轴位移大于Y轴位移，那么将事件交给横向的RecycleView处理。

                if (distanceX >= distanceY) {
//                    mIsVpDragger = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:                // 初始化标记
//                mIsVpDragger = false;
                break;
        }
        // 如果是Y轴位移大于X轴，事件交给自己处理。
        return super.onInterceptTouchEvent(ev);
    }

}
