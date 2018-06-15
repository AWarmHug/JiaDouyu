package com.warm.livelive.widget.recycleview2.adpter;

import com.warm.livelive.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 51hs_android
 * 时间: 2017/3/7
 * 简介: RecycleView.Adapter基类，添加几个数据添加刷新去除方法和点击事件,如果添加了头部，需要额外处理，并没有处理头部的情况
 */

public abstract class ClickAdapter<T, VH extends BaseViewHolder> extends BaseAdapter<T, VH> {

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
//        super.onBindViewHolder(holder, position, payloads);
        holder.setItemClickListener(onItemClickListener, getHeaderCount());
        onBindItem(holder, position, payloads);
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {
        onBindItem(holder, position, new ArrayList<>());
        holder.setItemClickListener(onItemClickListener, getHeaderCount());
    }


    protected abstract void onBindItem(VH holder, int position, List<Object> payloads);


}
