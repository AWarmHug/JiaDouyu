package com.warm.livelive.widget.recyadapter.loadview;


import com.warm.livelive.error.CustomException;

/**
 * 作者: 51hs_android
 * 时间: 2017/8/3
 * 简介:
 */

public interface OnLoadFinish {

    /**
     * 加载失败
     */
    public static final int FAIL = -1;
    /**
     * 加载成功
     */
    public static final int SUCCESS = 0;
    /**
     * 加载成功，但是没有数据了
     */
    public static final int NO_DATA = 1;

    /**
     * 正在加载状态,
     */
    public static final int LOADING = 2;


    void setLoadState(int state);


    void setLoadState(CustomException e);



}
