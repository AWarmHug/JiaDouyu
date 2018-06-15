package com.warm.livelive.base;

/**
 * 作者：warm
 * 时间：2018-06-14 14:48
 * 描述：
 */
public interface BasePresenter<V> {

    void attach(V view);

    void detach();
}
