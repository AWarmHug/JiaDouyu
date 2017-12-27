package com.warm.livelive.widget.recyadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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

    public void setItemClickListener(final OnItemClickListener onItemTouchListener, final int headerNum) {
        if (onItemTouchListener != null) {

            RxView.clicks(itemView).throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(@NonNull Object o) throws Exception {
                            onItemTouchListener.itemClick(getAdapterPosition()-headerNum);
                        }
                    });
        }
    }


}