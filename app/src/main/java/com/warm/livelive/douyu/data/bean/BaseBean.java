package com.warm.livelive.douyu.data.bean;

/**
 * 作者：warm
 * 时间：2017-12-25 15:59
 * 描述：
 */

public class BaseBean<Bean> {
    private int error;
    private Bean data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Bean getData() {
        return data;
    }

    public void setData(Bean data) {
        this.data = data;
    }
}
