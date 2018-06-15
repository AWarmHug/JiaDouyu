package com.warm.livelive.widget.recycleview2.adpter;

import android.support.annotation.IntRange;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-01-15 09:10
 * 描述：扩展Adapter 增加load、尾部（必须增加），支持添加一个头部（可有可无，由{@link #getHeaderCount()}==1）控制
 */

public abstract class ExpandAdapter<T> extends ClickAdapter<T, BaseViewHolder> {

    /**
     * 头部开始的数据 一个头部-100，第二个是-101，第三个是-102，以此类推
     */
    public static final int HEADER_START = -100;
    public static final int EMPTY = -99;
    public static final int TAIL = -98;



    @Override
    protected final void onBindItem(BaseViewHolder holder, int position, List<Object> payloads) {
        if (getItemViewType(position) <= HEADER_START) {
            onBindHeaderItem(holder, position);
        } else {
            switch (getItemViewType(position)) {
                case EMPTY:
                    onBindEmptyItem(holder);
                    break;
                case TAIL:
                    onBindTailItem(holder);
                    break;
                default:
                    onBindDefaultItem(holder, position - getHeaderCount(), payloads);
                    break;
            }
        }
    }

    protected abstract void onBindDefaultItem(BaseViewHolder holder, int position, List<Object> payloads);

    protected abstract void onBindTailItem(BaseViewHolder holder);

    protected abstract void onBindHeaderItem(BaseViewHolder holder, int position);

    protected abstract void onBindEmptyItem(BaseViewHolder holder);

    @Override
    public final int getBottomCount() {
        return 1;
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType <= HEADER_START) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else {
            switch (viewType) {
                case EMPTY:
                    return onCreateEmptyViewHolder(parent);
                case TAIL:
                    return onCreateTailViewHolder(parent);
                default:
                    return onCreateDefaultViewHolder(parent, viewType);
            }
        }
    }

    protected abstract BaseViewHolder onCreateDefaultViewHolder(ViewGroup parent, int viewType);

    protected abstract BaseViewHolder onCreateTailViewHolder(ViewGroup parent);

    /**
     * 如果添加了头部，需要重新这个方法
     *
     * @param parent   parent
     * @param viewType <=-100 {@link #getHeaderCount()}、{@link #HEADER_START} 组合使用
     * @return
     */
    protected abstract BaseViewHolder onCreateHeaderViewHolder(ViewGroup parent, @IntRange(to = HEADER_START) int viewType);

    protected abstract BaseViewHolder onCreateEmptyViewHolder(ViewGroup parent);


    @Override
    public final int getItemViewType(int position) {
        if (isEmpty()) {
            return EMPTY;
        } else if (!isEmpty() && getHeaderCount() != 0 && position < getHeaderCount()) {
            return HEADER_START - position;
        } else if (!isEmpty() && getBottomCount() != 0 && position == (getItemCount() - 1)) {
            return TAIL;
        } else {
            return getDefaultItemViewType(position);
        }
    }

    public int getDefaultItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (isEmpty()) {
            return 1;
        } else {
            return super.getItemCount();
        }
    }
}
