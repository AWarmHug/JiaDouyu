package com.warm.playerlib.player;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

/**
 * 作者：warm
 * 时间：2017-12-18 16:48
 * 描述：装载到{@link JustVideoPlayer}中，所以需要设置MATCH_PARENT
 */

public class JustTextureView extends TextureView {
    private static final String TAG = "JustTextureView";

    private int mVideoWidth;
    private int mVideoHeight;

    private int scaleType;

    public JustTextureView(Context context) {
        this(context, null);
    }

    public JustTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JustTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setRotation(float rotation) {
        if (rotation != getRotation()) {
            super.setRotation(rotation);
            requestLayout();
        }
    }

    public void setSize(int videoWidth, int videoHeight) {
        this.mVideoWidth = videoWidth;
        this.mVideoHeight = videoHeight;
    }

    public void setScaleType(int scaleType) {
        this.scaleType = scaleType;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
        Log.d(TAG, String.format("onMeasure: width=%d,height=%d", width, height));
        //宽度是否比高度长，长的那边始终长，所以需要判断，比如4：3，如果宽度长，那么宽4高3，否则高4宽3，
        boolean isWidthLong = mVideoWidth > mVideoHeight;

        switch (scaleType) {
            case JustVideoPlayer.SCALE_4_3:
                //设置一边铺满，另一半按比例
                if (isWidthLong) {
                    height = width * 3 / 4;
                } else {
                    width = height * 3 / 4;
                }
                break;
            case JustVideoPlayer.SCALE_16_9:
                if (isWidthLong) {
                    height = width * 9 / 16;
                } else {
                    width = height * 9 / 16;
                }
                break;
            case JustVideoPlayer.SCALE_WRAP_CONTENT:
                width = mVideoWidth;
                height = mVideoHeight;
                break;
            case JustVideoPlayer.SCALE_MATCH_PARENT:
                //计算宽高，保证其中一个铺满屏幕，另一方按比例
            default:
                float scale = isWidthLong ? (float) width / mVideoWidth : (float) height / mVideoHeight;
                if (isWidthLong){
                    height = (int) (scale * mVideoHeight);
                }else {
                    width = (int) (scale * mVideoWidth);
                }
                break;
        }
        setMeasuredDimension(width, height);
    }

}
