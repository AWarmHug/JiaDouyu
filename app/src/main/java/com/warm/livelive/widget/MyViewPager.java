package com.warm.livelive.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：warm
 * 时间：2018-06-12 14:29
 * 描述：
 */
public class MyViewPager extends ViewPager {


    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(@Nullable PagerAdapter adapter) {
        super.setAdapter(adapter);
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //实际计算得出的宽高
        int measureHeight;


        int childCount = getChildCount();

        int y = getPaddingTop() + getPaddingBottom();

        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                ViewGroup.LayoutParams lp = child.getLayoutParams();

                int childHeight = 0, hMargin = 0;
                if (lp instanceof MarginLayoutParams) {
                    MarginLayoutParams childLP = (MarginLayoutParams) lp;
                    /**
                     * 如果传入widthUsed,当使用wrap_content，会自动适配为最小宽度，会使一行最边缘的控件宽度变为 parent#Width-widthUsed;
                     * 这个widthUsed和heightUsed,当前横 纵已经使用了长度，一般用于设置权重之后，计算剩余可以摆放的位置，如果没有权重只用传0就可以，
                     * 可以看{@link android.widget.LinearLayout#measureHorizontal（1018行，1117行）和measureChildBeforeLayout}
                     */
                    measureChildWithMargins(child, widthMeasureSpec, 0/*widthUsed*/, heightMeasureSpec, /*heightUsed*/0);
                    hMargin = childLP.topMargin + childLP.bottomMargin;
                } else {
                    measureChild(child, widthMeasureSpec, heightMeasureSpec);
                }
                childHeight = child.getMeasuredHeight() + hMargin;
                y += childHeight;
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = heightSize;
        } else {
            measureHeight = y;
        }

        // 设置容器所需的宽度和高度
        setMeasuredDimension(widthMeasureSpec, measureHeight);
    }
}