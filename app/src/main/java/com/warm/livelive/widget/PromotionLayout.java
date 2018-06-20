package com.warm.livelive.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warm.livelive.LiveApp;
import com.warm.livelive.R;
import com.warm.livelive.douyu.data.bean.Promotion;
import com.warm.livelive.douyu.data.bean.Slide;
import com.warm.livelive.utils.NumUtil;
import com.warm.livelive.widget.viewpager.LoopPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：warm
 * 时间：2018-06-14 15:18
 * 描述：
 */
public class PromotionLayout extends LinearLayout {


    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_de)
    TextView tvDe;
    @BindView(R.id.bt_action)
    Button btAction;

    public PromotionLayout(Context context) {
        this(context, null);
    }

    public PromotionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PromotionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_promotion, this);
        ButterKnife.bind(this);
    }

    public void setSlide(List<Slide> slide,OnItemClickListener listener) {
        if (slide!=null&&slide.size()!=0) {
            if (!pager.isShown()) {
                pager.setVisibility(VISIBLE);
            }
            List<String> pics = new ArrayList<>();
            for (int i = 0; i < slide.size(); i++) {
                pics.add(slide.get(i).getResource());
            }
            LoopPagerAdapter adapter = new LoopPagerAdapter(getContext(), pics);
            pager.setAdapter(adapter);
            adapter.setOnIteClickListener(listener);
        }
    }

    public void setPromotion(Promotion promotion, OnClickListener listener) {
        if (promotion!=null&&promotion.getId()!=0) {
            if (!line.isShown()) {
                line.setVisibility(VISIBLE);
            }
            LiveApp.getInstance().getImageLoader().loadImage(getContext(), ivBg, promotion.getBanner());
            LiveApp.getInstance().getImageLoader().loadImage(getContext(), ivIcon, promotion.getIcon());
            tvTitle.setText(promotion.getApp_name());
            tvDe.setText("下载");
            tvDe.append(NumUtil.mini(promotion.getApp_downloads()));
            tvDe.append("|");
            tvDe.append(String.valueOf(promotion.getApp_size()));
            tvDe.append("M");
            btAction.setOnClickListener(listener);
        }
    }


}
