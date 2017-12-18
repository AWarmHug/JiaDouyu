package com.warm.playerlib.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warm.playerlib.R;

import java.util.Formatter;
import java.util.Locale;


/**
 * Created by warm on 17/5/6.
 */

public class TitleBar extends LinearLayout {
    private ImageButton bt_back;
    private TextView tv_title, tv_totalTime;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs, defStyle);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle) {
        inflate(context, R.layout.bar_title, this);
        bt_back = (ImageButton) findViewById(R.id.bt_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_totalTime = (TextView) findViewById(R.id.tv_totalTime);
    }

    public void setTotalTime(int m) {
        tv_totalTime.setText(stringForTime(m));

    }

    private String stringForTime(int timeMs) {

        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }


    public void setTitle(CharSequence title) {
        tv_title.setText(title);
    }

    public CharSequence getTitle() {
        return tv_title.getText();
    }


}
