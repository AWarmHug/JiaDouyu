package com.warm.livelive.widget.recycleview2.adpter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 51hs_android
 * 时间: 2017/3/7
 * 简介: RecycleView.Adapter基类，添加几个数据添加刷新去除方法
 * 增加{@link #getHeaderCount()}、{@link #getBottomCount()}来设置头部和尾部的数量
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mDatas;


    public List<T> getDatas() {
        return mDatas;
    }


    public BaseAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public BaseAdapter() {
        this(new ArrayList<T>());
    }

    /**
     * @return 头部总数
     */
    public abstract int getHeaderCount();

    /**
     * @return 尾部总数
     */
    public abstract int getBottomCount();


    @Override
    public int getItemCount() {
        return isEmpty() ? 0 : getHeaderCount() + mDatas.size() + getBottomCount();
    }


    /**
     * 在position位置上添加有新的数据
     * 在添加后，需要调用{@link #notifyItemRangeChanged(int, int)}来刷新该位置后面的数据。防止其他position受干扰
     *
     * @param position position 在list中的位置
     * @param t        T
     */
    public void insertItem(int position, T t) {
        mDatas.add(position, t);
        notifyItemInserted(position + getHeaderCount());
        notifyItemRangeChanged(position + getHeaderCount(), getItemCount() - position);
    }

    /**
     * @param position position 在list中的位置
     */
    public void refreshItem(int position) {
        notifyItemChanged(position + getHeaderCount());
    }

    public void refreshItem(int position, Object payload) {
        notifyItemChanged(position + getHeaderCount(), payload);

    }


    /**
     * 在顶部（0位置上）添加一个新数据
     *
     * @param t T
     */
    public void insertTopData(T t) {
        insertItem(getHeaderCount(), t);
    }

    /**
     * 在尾部增加一组数据
     *
     * @param tList 一组数据
     */
    public void insertRange(List<T> tList) {
        boolean empty = isEmpty();
        int size = mDatas.size();
        mDatas.addAll(tList);
        if (empty) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeInserted(size + getHeaderCount(), tList.size());
            // TODO: 2017/8/8 是否需要？在单个操作的时候，需要调用批量跳转操作之后的所有数据，但是在批量操作的时候好像不需要
//            notifyItemRangeChanged(list.size(), tList.size());
        }
    }

    public void refreshAll() {
        refreshAll(getDatas());
    }

    public void refreshAll(List<T> tList) {
//        removeAll();
//        insertRange(tList);
        mDatas.clear();
        mDatas.addAll(tList);
        notifyDataSetChanged();
    }

    /**
     * 移除position位置上的数据
     * 在移除后，需要调用{@link #notifyItemRangeChanged(int, int)}来刷新该位置后面的数据。防止其他position受干扰
     *
     * @param position
     */
    public void removeItem(int position) {
        if (checkEmpty()) {
            return;
        }
//        list.remove(position);
//        notifyItemRemoved(position);
//        if (position != list.size()) {
//            notifyItemRangeChanged(position, list.size() - position);
//        }
//        List<Integer> p = new ArrayList<>();
//        p.add(position);
//        removeContRange(p);
        int size= mDatas.size();
        mDatas.remove(position);
        notifyItemRemoved(position + getHeaderCount());
        notifyItemRangeChanged(position, size- 1 - position);
    }


    /**
     * 移除不连续的
     *
     * @param positions
     */
    public void removeNoContRange(List<Integer> positions) {
        if (checkEmpty()) {
            return;
        }
        List<T> items = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            items.add(mDatas.get(positions.get(i)));
            notifyItemRemoved(positions.get(i) - i + getHeaderCount());
        }
        mDatas.removeAll(items);
        notifyItemRangeChanged(positions.get(0), mDatas.size());
    }

    /**
     * 移除连续的
     *
     * @param positions
     */
    public void removeContRange(List<Integer> positions) {
        if (checkEmpty()) {
            return;
        }
        List<T> items = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            items.add(mDatas.get(positions.get(i)));
        }
        mDatas.removeAll(items);
        notifyItemRangeRemoved(positions.get(0), positions.size() + getHeaderCount());
        notifyItemRangeChanged(positions.get(0), mDatas.size());
    }

    /**
     * 移除所有数据
     */
    public void removeAll() {
        if (checkEmpty()) {
            return;
        }
        int size = mDatas.size();
        mDatas.clear();
        notifyItemRangeRemoved(getHeaderCount(), size);
    }

    public boolean isEmpty() {
        return mDatas == null || mDatas.size() == 0;

    }

    private boolean checkEmpty() {
        return isEmpty();
    }


}
