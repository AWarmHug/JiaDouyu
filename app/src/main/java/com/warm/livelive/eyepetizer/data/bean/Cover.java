package com.warm.livelive.eyepetizer.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-17 04:43
 * 描述：
 */
public class Cover {

    /**
     * feed : http://img.kaiyanapp.com/1bc5fd14bfb26df37af401a154edeca0.jpeg?imageMogr2/quality/100
     * detail : http://img.kaiyanapp.com/1bc5fd14bfb26df37af401a154edeca0.jpeg?imageMogr2/quality/100
     * blurred : http://img.kaiyanapp.com/536ffac463b7de007fc3b31fb880ae1f.jpeg?imageMogr2/quality/100
     * sharing : null
     * homepage : null
     */

    private String feed;
    private String detail;
    private String blurred;
    private String sharing;
    private String homepage;

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBlurred() {
        return blurred;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }

    public String getSharing() {
        return sharing;
    }

    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}
