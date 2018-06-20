package com.warm.livelive.eyepetizer.data.bean;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-18 17:01
 * 描述：
 */
public class PageData extends BaseBean {

    /**
     * itemList : []
     * count : 16
     * total : 0
     * nextPageUrl : http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=1&isTag=true&adIndex=6
     * adExist : false
     */

    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
    private List<Content> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public boolean isAdExist() {
        return adExist;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }

    public List<Content> getItemList() {
        return itemList;
    }

    public void setItemList(List<Content> itemList) {
        this.itemList = itemList;
    }
}
