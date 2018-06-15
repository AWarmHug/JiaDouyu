package com.warm.livelive.douyu.data.bean.douyu;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-14 18:46
 * 描述：
 */
public class ActivityList {
    private int total;
    private List<Activity> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Activity> getList() {
        return list;
    }

    public void setList(List<Activity> list) {
        this.list = list;
    }
}
