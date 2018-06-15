package com.warm.livelive.widget.recycleview2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.warm.livelive.R;
import com.warm.livelive.widget.load.LoadView;
import com.warm.livelive.widget.recycleview2.adpter.BaseViewHolder;
import com.warm.livelive.widget.recycleview2.adpter.ExpandAdapter;

/**
 * 作者：warm
 * 时间：2018-06-13 13:41
 * 描述：
 */
public abstract class DouyuExpandAdapter<T> extends ExpandAdapter<T> implements LoadView {

    private boolean mLoadState;
    private String mLoadMsg;
    private View.OnClickListener mClickListener;

    @Override
    protected void onBindTailItem(BaseViewHolder holder) {
        if (holder instanceof TailViewHolder) {
            TailViewHolder tailViewHolder = (TailViewHolder) holder;
            tailViewHolder.itemView.setOnClickListener(mClickListener);
            tailViewHolder.tv.setText(mLoadMsg);
            if (mLoadState) {
                tailViewHolder.bar.smoothToShow();
            } else {
                tailViewHolder.bar.smoothToHide();
            }
        }

    }

    @Override
    protected void onBindHeaderItem(BaseViewHolder holder, int position) {

    }

    @Override
    protected void onBindEmptyItem(BaseViewHolder holder) {
        if (holder instanceof EmptyViewHolder) {
            EmptyViewHolder tailViewHolder = (EmptyViewHolder) holder;
            tailViewHolder.itemView.setOnClickListener(mClickListener);
            tailViewHolder.tv.setText(mLoadMsg);
            if (mLoadState) {
                tailViewHolder.bar.smoothToShow();
            } else {
                tailViewHolder.bar.smoothToHide();
            }
        }
    }


    @Override
    public void loadBegan(String msg) {
        this.mLoadState = true;
        this.mLoadMsg = msg;
        this.mClickListener = null;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public void loadSuccess(String msg) {
        this.mLoadState = false;
        this.mLoadMsg = msg;
        this.mClickListener = null;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public void loadFail(String msg, View.OnClickListener clickListener) {
        this.mLoadState = false;
        this.mLoadMsg = msg;
        this.mClickListener = clickListener;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    protected BaseViewHolder onCreateTailViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tail_douyu, parent, false);
        return new TailViewHolder(view);
    }

    @Override
    protected BaseViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected BaseViewHolder onCreateEmptyViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_empty_douyu, parent, false);
        return new EmptyViewHolder(view);
    }

    @Override
    public int getHeaderCount() {
        return 0;
    }

    static class EmptyViewHolder extends BaseViewHolder {
        AVLoadingIndicatorView bar;
        TextView tv;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            bar = itemView.findViewById(R.id.bar);
            tv = itemView.findViewById(R.id.tv);
        }

    }

    static class TailViewHolder extends BaseViewHolder {
        AVLoadingIndicatorView bar;
        TextView tv;

        public TailViewHolder(View itemView) {
            super(itemView);
            bar = itemView.findViewById(R.id.bar);
            tv = itemView.findViewById(R.id.tv);
        }

    }

}
