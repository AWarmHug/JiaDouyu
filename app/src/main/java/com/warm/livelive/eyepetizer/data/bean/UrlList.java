package com.warm.livelive.eyepetizer.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-18 16:12
 * 描述：
 */
public class UrlList {
    /**
     * name : aliyun
     * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=1130&resourceType=video&editionType=high&source=aliyun
     * size : 60891024
     */

    private String name;
    private String url;
    private int size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
