package com.warm.livelive.widget.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.warm.livelive.R;
import com.warm.livelive.utils.DisplayUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 作者：warm
 * 时间：2018-06-14 11:25
 * 描述：
 * <attr name="indicatorSrc" format="reference" />
 * <attr name="indicatorHeight" format="dimension|reference" />
 * <attr name="indicatorWidth" format="dimension|reference" />
 * <attr name="indicatorSelectedHeight" format="dimension|reference" />
 * <attr name="indicatorSelectedWidth" format="dimension|reference" />
 * <attr name="indicatorSpace" format="dimension|reference" />
 */
public class ViewPagerIndicator extends LinearLayout {
    public static final int MAX_SIZE = 50;
    public Pools.Pool<View> mViewPool = new Pools.SimplePool<>(8);

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private DataSetObserver mPagerAdapterObserver;
    private IndicatorOnPageChangeListener mPageChangeListener;
    private ArrayList<View> mViews = new ArrayList<>();
    private int mSrcId;
    private int[] indicatorSize = new int[2];
    private int[] indicatorSelectedSize = new int[2];
    private int indicatorSpace;
    private int mCount;
    private int selectedPosition;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.ViewPagerIndicator);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        ThemeUtils.checkAppCompatTheme(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator, defStyleAttr, defStyleRes);
        mSrcId = a.getResourceId(R.styleable.ViewPagerIndicator_indicatorSrc, 0);
//        if (drawable!=null&&drawable instanceof StateListDrawable){
//            StateListDrawable drawable1= (StateListDrawable) drawable;
//        }
        indicatorSize[0] = a.getDimensionPixelOffset(R.styleable.ViewPagerIndicator_indicatorWidth, dp2px(2));
        indicatorSize[1] = a.getDimensionPixelOffset(R.styleable.ViewPagerIndicator_indicatorHeight, dp2px(2));

        indicatorSelectedSize[0] = a.getDimensionPixelOffset(R.styleable.ViewPagerIndicator_indicatorSelectedWidth, dp2px(2));
        indicatorSelectedSize[1] = a.getDimensionPixelOffset(R.styleable.ViewPagerIndicator_indicatorSelectedHeight, dp2px(2));

        indicatorSpace = a.getDimensionPixelOffset(R.styleable.ViewPagerIndicator_indicatorSpace, dp2px(2));
        a.recycle();


    }


    public int dp2px(float dipValue) {
        return DisplayUtil.dp2px(getContext(), dipValue);
    }


    public void setupWithViewPager(ViewPager viewPager) {
        setupWithViewPager(0, viewPager, true);
    }

    public void setupWithViewPager(int count, ViewPager viewPager, boolean autoRefresh) {
        this.mCount = count;

        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(mPageChangeListener);
        }
        if (viewPager != null) {

            mViewPager = viewPager;
            if (mPageChangeListener == null) {
                mPageChangeListener = new IndicatorOnPageChangeListener(this);
            }
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                setPagerAdapter(adapter, autoRefresh);
            }
            mViewPager.addOnPageChangeListener(mPageChangeListener);
            mPageChangeListener.reset();
        }
    }

    private void setPagerAdapter(PagerAdapter adapter, boolean addObserver) {
        if (mPagerAdapter != null && mPagerAdapterObserver != null) {
            mPagerAdapter.unregisterDataSetObserver(mPagerAdapterObserver);
        }
        mPagerAdapter = adapter;

        if (addObserver && adapter != null) {
            if (mPagerAdapterObserver == null) {
                mPagerAdapterObserver = new PagerAdapterObserver();
            }
            mPagerAdapter.registerDataSetObserver(mPagerAdapterObserver);
        }
        //生成item
        populateFromPagerAdapter();
    }

    private void populateFromPagerAdapter() {
        removeAllItem();

        if (mPagerAdapter != null) {
            if (mCount == 0) {
                if (mPagerAdapter.getCount() > MAX_SIZE) {
                    throw new IllegalArgumentException("item数量超过20,请检查");
                } else {
                    this.mCount = mPagerAdapter.getCount();
                }
            }
            //创建
            for (int i = 0; i < mCount; i++) {
                View item = newItem();
                addView(item, createLayoutParamsForItem());
                mViews.add(item);
            }
            //设置当前Viewpager已选中的item
            if (mViewPager != null && mCount > 0) {
                int curPosition = mViewPager.getCurrentItem();
                mViews.get(curPosition).setSelected(true);
                selectedPosition = curPosition;
            }
        }
    }

    private LayoutParams createLayoutParamsForItem() {
        LayoutParams params = new LayoutParams(indicatorSize[0], indicatorSize[1]);
        params.leftMargin = indicatorSpace / 2;
        params.rightMargin = indicatorSpace / 2;
        return params;
    }

    private View newItem() {
        View view = mViewPool.acquire();
        if (view == null) {
            view = new View(getContext());
            if (mSrcId != 0) {
                ViewCompat.setBackground(
                        view, AppCompatResources.getDrawable(getContext(), mSrcId));
            }
        }
        return view;
    }

    private void removeAllItem() {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            removeViewAt(i);
        }

        for (int i = 0; i < mViews.size(); i++) {
            View item = mViews.get(i);
            item.setSelected(false);
            mViews.remove(i);
            mViewPool.release(item);
        }

    }

    public static class IndicatorOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private WeakReference<ViewPagerIndicator> reIndicator;

        public IndicatorOnPageChangeListener(ViewPagerIndicator indicator) {
            reIndicator = new WeakReference<>(indicator);
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
            ViewPagerIndicator indicator = reIndicator.get();

            int curPosition = indicator.selectedPosition;
            if (indicator.mViews.get(curPosition).isSelected()) {
                indicator.mViews.get(curPosition).setSelected(false);
            }
            LayoutParams params = (LayoutParams) indicator.mViews.get(curPosition).getLayoutParams();
            params.width = indicator.indicatorSize[0];
            params.height = indicator.indicatorSize[1];
            indicator.mViews.get(curPosition).setLayoutParams(params);

            indicator.mViews.get(position).setSelected(true);
            LayoutParams params2 = (LayoutParams) indicator.mViews.get(position).getLayoutParams();
            params2.width = indicator.indicatorSelectedSize[0];
            params2.height = indicator.indicatorSelectedSize[1];
            indicator.mViews.get(position).setLayoutParams(params2);

            indicator.selectedPosition = position;

        }


        void reset() {

        }
    }

    class PagerAdapterObserver extends DataSetObserver {
        public PagerAdapterObserver() {
        }

        @Override
        public void onChanged() {
            populateFromPagerAdapter();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
            populateFromPagerAdapter();

        }
    }

}
