package com.warm.livelive.widget.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.warm.livelive.MyApp;
import com.warm.livelive.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-14 12:46
 * 描述：
 */
public class LoopPagerAdapter extends PagerAdapter {
    Context mContext;
    private List<View> views;
    private int itemCount;
    private OnItemClickListener mOnItemClickListener;

    public void setOnIteClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private ImageView getImageView(String url, final int position) {
        ImageView imageView = new ImageView(mContext);


        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        imageView.setOnClickListener(v -> {
            if (mOnItemClickListener !=null){
                mOnItemClickListener.itemClick(position);
            }
        });
        MyApp.getInstance().getImageLoader().loadImage(mContext,imageView,url);
        return imageView;
    }

    public LoopPagerAdapter(Context context, List<String> urls) {
        this.mContext = context;
        List<View> views = new ArrayList<>();
        itemCount = urls.size();
        //主要是解决当item为小于3个的时候滑动有问题，这里将其拼凑成3个以上
        if (itemCount < 1) {//当item个数0
            throw new IllegalStateException("item count not equal zero");
        } else if (itemCount < 2) { //当item个数为1
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(0), 0));
        } else if (itemCount < 3) {//当item个数为2
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(1), 1));
            views.add(getImageView(urls.get(0), 0));
            views.add(getImageView(urls.get(1), 1));
        } else {
            for (int i = 0; i < urls.size(); i++) {
                views.add(getImageView(urls.get(i), i));
            }
        }
        this.views = views;
    }

    @Override
    public int getCount() {
        //Integer.MAX_VALUE = 2147483647
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (views.size() > 0) {
            //position % itemView.size()是指虚拟的position会在[0，itemView.size()）之间循环
            View view = views.get(position % views.size());
            if (container.equals(view.getParent())) {
                container.removeView(view);
            }
            container.addView(view);
            return view;
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
