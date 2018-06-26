package com.warm.livelive.douyu.ui.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.warm.livelive.MyApp;
import com.warm.livelive.R;
import com.warm.livelive.douyu.data.bean.search.Anchor;
import com.warm.livelive.utils.NumUtil;
import com.warm.livelive.widget.recycleview2.DouyuExpandAdapter;
import com.warm.livelive.widget.recycleview2.adpter.BaseViewHolder;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-26 21:14
 * 描述：
 */
public class AnchorAdapter extends DouyuExpandAdapter<Anchor> {


    @Override
    protected void onBindDefaultItem(BaseViewHolder holder, int position, List<Object> payloads) {
        RecFavorViewHolder viewHolder = (RecFavorViewHolder) holder;
        MyApp.getInstance().getImageLoader().loadImage(viewHolder.ivAvatar.getContext(), viewHolder.ivAvatar, mDatas.get(position).getAvatar(), 4);
        viewHolder.tvName.setText(mDatas.get(position).getNickname());
        viewHolder.tvLevel.setText(mDatas.get(position).getCateName());
        viewHolder.tvFollowNum.append(NumUtil.mini(mDatas.get(position).getFollow()));
        viewHolder.btIsLive.setText(mDatas.get(position).getIsLive() == 1 ? "直播中" : "休息中");
        viewHolder.tvOnline.setText(String.format("热力值%s", NumUtil.mini(mDatas.get(position).getPopularity())));
    }

    @Override
    protected BaseViewHolder onCreateDefaultViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_recfavors, parent, false);
        return new RecFavorViewHolder(view);
    }
}
