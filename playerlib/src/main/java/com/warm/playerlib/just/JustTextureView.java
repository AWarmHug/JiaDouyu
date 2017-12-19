package com.warm.playerlib.just;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

/**
 * 作者：warm
 * 时间：2017-12-18 16:48
 * 描述：装载到{@link JustVideoView}中，所以需要设置MATCH_PARENT
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
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.black));
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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
        Log.d(TAG, "onMeasure: width=" + width);
        Log.d(TAG, "onMeasure: height=" + height);
        //宽度是否比高度长，长的那边始终长，所以需要判断，比如4：3，如果宽度长，那么宽4高3，否则高4宽3，
        boolean isWidthLong = mVideoWidth > mVideoHeight;
        float scale = isWidthLong ? (float) width / mVideoWidth : (float) height / mVideoHeight;

        //必须保证视频不拉伸。
        switch (scaleType) {
            case JustVideoView.SCALE_4_3:
                //设置一边铺满，另一半按比例
                if (isWidthLong) {
                    width = (int) (mVideoWidth * scale);
                    height = width * 3 / 4;
                } else {
                    height = (int) (mVideoHeight * scale);
                    width = height * 3 / 4;
                }
                break;
            case JustVideoView.SCALE_16_9:
                if (isWidthLong) {
                    width = (int) (mVideoWidth * scale);
                    height = width * 9 / 16;
                } else {
                    height = (int) (mVideoHeight * scale);
                    width = height * 9 / 16;
                }
                break;
            case JustVideoView.SCALE_WRAP_CONTENT:
                width = mVideoWidth;
                height = mVideoHeight;
                break;
            case JustVideoView.SCALE_MATCH_PARENT:
                //计算宽高，保证其中一个铺满屏幕，另一方按比例
            default:
                width = (int) (scale * mVideoWidth);
                height = (int) (scale * mVideoHeight);
                break;

        }
        setMeasuredDimension(width, height);

    }
}
