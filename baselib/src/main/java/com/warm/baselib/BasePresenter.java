package com.warm.baselib;

/**
 * Created by warm on 17/6/17.
 * 这里的V 为 View
 */

public interface BasePresenter<V extends BaseView> {



    /**
     * 粘贴
     * @param view
     */
    void attach(V view);

    /**
     *
     * 取消粘贴
     */
    void detach();



}
