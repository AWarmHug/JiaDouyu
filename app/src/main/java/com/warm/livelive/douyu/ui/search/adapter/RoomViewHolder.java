package com.warm.livelive.douyu.ui.search.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.warm.livelive.MyApp;
import com.warm.livelive.R;
import com.warm.livelive.douyu.data.bean.search.Room;
import com.warm.livelive.utils.NumUtil;
import com.warm.livelive.widget.recycleview2.adpter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：warm
 * 时间：2018-06-22 22:18
 * 描述：
 */
public class RoomViewHolder extends BaseViewHolder {
    @BindView(R.id.iv_pic)
    public ImageView ivPic;
    @BindView(R.id.tv_heat)
    public TextView tvHeat;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_name)
    public TextView tvName;
    @BindView(R.id.isLive)
    public TextView isLive;


    public RoomViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void showItem(Room room){
        MyApp.getInstance().getImageLoader().loadImage(ivPic.getContext(), ivPic, room.getRoomSrc());
        isLive.setVisibility(room.getIsLive() == 1 ? View.VISIBLE : View.GONE);
        tvName.setText(room.getNickname());
        tvTitle.setText(room.getNoRed());
        tvHeat.setText(String.format("%s热度", NumUtil.mini(room.getPopularity())));
    }
}
