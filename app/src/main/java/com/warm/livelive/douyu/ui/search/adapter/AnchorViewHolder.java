package com.warm.livelive.douyu.ui.search.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.warm.livelive.R;
import com.warm.livelive.widget.recycleview2.adpter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：warm
 * 时间：2018-06-22 22:18
 * 描述：
 */
public class AnchorViewHolder extends BaseViewHolder {
    @BindView(R.id.iv_avatar)
    public ImageView ivAvatar;
    @BindView(R.id.isLive)
    public TextView isLive;
    @BindView(R.id.tv_name)
    public TextView tvName;
    @BindView(R.id.tv_followNum)
    public TextView tvFollow;


    public AnchorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
