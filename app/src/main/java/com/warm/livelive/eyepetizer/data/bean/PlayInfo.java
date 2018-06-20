package com.warm.livelive.eyepetizer.data.bean;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-18 16:10
 * 描述：
 */
public class PlayInfo {

    /**
     * height : 720
     * width : 1280
     * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=1130&resourceType=video&editionType=high&source=aliyun","size":60891024},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=1130&resourceType=video&editionType=high&source=qcloud","size":60891024},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=1130&resourceType=video&editionType=high&source=ucloud","size":60891024}]
     * name : 高清
     * type : high
     * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=1130&resourceType=video&editionType=high&source=aliyun
     */

    private int height;
    private int width;
    private String name;
    private String type;
    private String url;
    private List<UrlList> urlList;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<UrlList> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<UrlList> urlList) {
        this.urlList = urlList;
    }

}
