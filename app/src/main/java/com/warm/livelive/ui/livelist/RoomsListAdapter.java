package com.warm.livelive.ui.livelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.warm.livelive.R;
import com.warm.livelive.data.bean.LiveRoom;
import com.warm.livelive.widget.recyadapter.BaseViewHolder;
import com.warm.livelive.widget.recyadapter.adapter.BaseAdapter;

import java.util.List;

/**
 * 作者：warm
 * 时间：2017-12-26 10:52
 * 描述：
 */

public class RoomsListAdapter extends BaseAdapter<LiveRoom,RoomsListAdapter.ViewHolder> {

    public RoomsListAdapter(List<LiveRoom> list) {
        super(list);
    }

    @Override
    public int getHeaderNum() {
        return 0;
    }

    @Override
    public int getBottomBum() {
        return 0;
    }

    @Override
    protected void onBindItem(ViewHolder holder, int position) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rooms_list,parent,false));
    }




    class ViewHolder extends BaseViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
