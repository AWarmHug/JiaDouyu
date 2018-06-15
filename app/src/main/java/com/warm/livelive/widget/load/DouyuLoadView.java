package com.warm.livelive.widget.load;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.warm.livelive.R;

/**
 * 作者：warm
 * 时间：2018-06-13 10:17
 * 描述：
 */
public class DouyuLoadView extends RelativeLayout implements LoadView {
    private AVLoadingIndicatorView mBar;
    private TextView mTv;


    public DouyuLoadView(Context context) {
        this(context, null);
    }

    public DouyuLoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DouyuLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DouyuLoadView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflate(context, R.layout.loadview_douyu, this);
        mBar = findViewById(R.id.bar);
        mTv = findViewById(R.id.tv);
    }

    @Override
    public void loadBegan(String msg) {
        mBar.smoothToShow();
        mTv.setText(msg);
        this.setVisibility(VISIBLE);

    }


    @Override
    public void loadSuccess(String msg) {
        this.setVisibility(GONE);
        mTv.setText(msg);
    }

    @Override
    public void loadFail(String msg, OnClickListener clickListener) {
        this.setVisibility(VISIBLE);
        mBar.smoothToHide();
        mTv.setText(msg);
        this.setOnClickListener(clickListener);
    }
}
