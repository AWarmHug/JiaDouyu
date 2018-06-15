package com.warm.livelive.widget.recycleview2.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.warm.livelive.widget.OnItemClickListener;

import butterknife.ButterKnife;

/**
 * 作者：warm
 * 时间：2017-12-26 12:50
 * 描述：
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setItemClickListener(final OnItemClickListener itemClickListener, final int headerNum) {
        //头部的数量，当我当前的位置>（头部数量-1）才是我真实的位置，尾部可以暂时不考虑
        if (itemClickListener != null && getAdapterPosition() > (headerNum - 1)) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.itemClick(getAdapterPosition() - headerNum);
                }
            });
        }
    }


}