package com.warm.livelive.widget.recyadapter.adapter;

import android.support.v7.widget.RecyclerView;

import com.warm.livelive.widget.recyadapter.BaseViewHolder;
import com.warm.livelive.widget.recyadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 51hs_android
 * 时间: 2017/3/7
 * 简介: RecycleView.Adapter基类，添加几个数据添加刷新去除方法和点击事件,如果添加了头部，需要额外处理，并没有处理头部的情况
 */

public abstract class BaseAdapter<T,VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> list;

    public List<T> getList() {
        return list;
    }

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BaseAdapter(List<T> list) {
        this.list = list;
    }


    public abstract int getHeaderNum();

    public abstract int getBottomBum();


    @Override
    public int getItemCount() {

        return list == null ? 0 : getHeaderNum() + list.size() + getBottomBum();
    }

    /**
     * 在position位置上添加有新的数据
     * 在添加后，需要调用{@link #notifyItemRangeChanged(int, int)}来刷新该位置后面的数据。防止其他position受干扰
     *
     * @param position
     * @param t
     */
    public void insertItem(int position, T t) {
        list.add(position, t);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, list.size());
    }

    public void refreshItem(int position) {
        notifyItemChanged(position);
    }


    /**
     * 在顶部（0位置上）添加一个新数据
     *
     * @param t
     */
    public void insertTopData(T t) {
        insertItem(0, t);
    }


    public void insertRange(List<T> tList) {
        if (list.size() == 0) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeInserted(list.size(), tList.size());
            // TODO: 2017/8/8 是否需要？在单个操作的时候，需要调用批量跳转操作之后的所有数据，但是在批量操作的时候好像不需要
//            notifyItemRangeChanged(list.size(), tList.size());
        }
        list.addAll(tList);

    }

    public void refreshAll() {
        refreshAll(getList());
    }

    public void refreshAll(List<T> tList) {
        removeAll();
        insertRange(tList);
    }

    /**
     * 移除position位置上的数据
     * 在移除后，需要调用{@link #notifyItemRangeChanged(int, int)}来刷新该位置后面的数据。防止其他position受干扰
     *
     * @param position
     */
    public void removeItem(int position) {
//        list.remove(position);
//        notifyItemRemoved(position);
//        if (position != list.size()) {
//            notifyItemRangeChanged(position, list.size() - position);
//        }
        List<Integer> p = new ArrayList<>();
        p.add(position);
        removeRange(p);
    }

    public void removeRange(List<Integer> positions) {
        List<T> items = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            items.add(list.get(positions.get(i)));
            notifyItemRemoved(positions.get(i) - i);
        }
        list.removeAll(items);

        notifyItemRangeChanged(positions.get(0), list.size());
    }

    @Override
    public final void onBindViewHolder(VH holder, int position) {

        onBindItem(holder,position);
    }

    protected abstract void onBindItem(VH holder, int position);

    /**
     * 移除所有数据
     */
    public void removeAll() {
        notifyItemRangeRemoved(0, list.size());
        list.clear();
    }




}
