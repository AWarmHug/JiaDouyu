package com.warm.livelive.widget.load;

import android.view.View;

/**
 * 作者：warm
 * 时间：2018-06-13 09:44
 * 描述：
 */
public interface LoadView {
    void loadBegan(String msg);
    void loadSuccess(String msg);
    void loadFail(String msg, View.OnClickListener clickListener);
}
