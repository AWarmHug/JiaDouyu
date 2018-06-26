package com.warm.livelive.douyu.ui.search.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.warm.livelive.MyApp;
import com.warm.livelive.R;
import com.warm.livelive.douyu.data.bean.search.Video;
import com.warm.livelive.utils.TimeUtil;
import com.warm.livelive.widget.recycleview2.adpter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：warm
 * 时间：2018-06-22 22:18
 * 描述：
 */
public class VideoViewHolder extends BaseViewHolder {
    @BindView(R.id.iv_pic)
    public ImageView ivPic;
    @BindView(R.id.tv_time)
    public TextView tvTime;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_name)
    public TextView tvName;


    public VideoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void showItem(Video video) {
        MyApp.getInstance().getImageLoader().loadImage(ivPic.getContext(), ivPic, video.getVideo_pic());
        tvTime.setText(TimeUtil.time2Duration(video.getVideo_duration()));
        tvTitle.setText(video.getTitle());
        tvName.setText(video.getNickname());
    }
}
