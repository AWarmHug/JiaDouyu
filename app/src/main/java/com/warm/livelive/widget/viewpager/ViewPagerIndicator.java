package com.warm.livelive.widget.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.warm.livelive.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 作者：warm
 * 时间：2018-06-14 11:25
 * 描述：
 */
public class ViewPagerIndicator extends LinearLayout{
    private ViewPager mViewPager;
    private IndicatorOnPageChangeListener mPageChangeListener;
    private ArrayList<ImageView> imageViews=new ArrayList<>();


    public ViewPagerIndicator(Context context) {
        this(context,null);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.ViewPagerIndicator);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        ThemeUtils.checkAppCompatTheme(context);

        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.ViewPagerIndicator,defStyleAttr,defStyleRes);


        a.recycle();

    }

    public void setupWithViewPager(ViewPager viewPager){
        if (mViewPager!=null){
            mViewPager.removeOnPageChangeListener(mPageChangeListener);
        }
        if (viewPager!=null){
            mViewPager=viewPager;
            if (mPageChangeListener==null){
                mPageChangeListener=new IndicatorOnPageChangeListener(this);
            }
            mPageChangeListener.reset();
        }

    }




    public static class IndicatorOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private WeakReference<ViewPagerIndicator> reIndicator;

        public IndicatorOnPageChangeListener(ViewPagerIndicator indicator) {
            reIndicator=new WeakReference<>(indicator);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {

        }

        @Override
        public void onPageScrolled(final int position, final float positionOffset,
                                   final int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(final int position) {

        }

        void reset() {

        }
    }


}
