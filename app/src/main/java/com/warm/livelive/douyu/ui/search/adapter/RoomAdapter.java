package com.warm.livelive.douyu.ui.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.warm.livelive.R;
import com.warm.livelive.douyu.data.bean.search.Room;
import com.warm.livelive.widget.recycleview2.DouyuExpandAdapter;
import com.warm.livelive.widget.recycleview2.adpter.BaseViewHolder;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-26 18:01
 * 描述：
 */
public class RoomAdapter extends DouyuExpandAdapter<Room> {

    @Override
    protected void onBindDefaultItem(BaseViewHolder holder, int position, List<Object> payloads) {
        RoomViewHolder roomViewHolder= (RoomViewHolder) holder;
        roomViewHolder.showItem(mDatas.get(position));
    }

    @Override
    protected BaseViewHolder onCreateDefaultViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_room, parent, false);
        return new RoomViewHolder(view);
    }


}
