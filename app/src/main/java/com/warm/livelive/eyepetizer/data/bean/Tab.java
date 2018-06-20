package com.warm.livelive.eyepetizer.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-17 15:04
 * 描述：
 */
public class Tab {
    /**
     * id : -1
     * name : 发现
     * apiUrl : http://baobab.kaiyanapp.com/api/v5/index/tab/discovery
     */

    private int id;
    private String name;
    private String apiUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
