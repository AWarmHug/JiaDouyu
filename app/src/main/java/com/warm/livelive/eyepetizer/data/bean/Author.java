package com.warm.livelive.eyepetizer.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-18 16:08
 * 描述：
 */
public class Author {

    /**
     * id : 2163
     * icon : http://img.kaiyanapp.com/9279c17b4da5ba5e7e4f21afb5bb0a74.jpeg
     * name : 开眼音乐精选
     * description : 全球最酷、最炫、最有态度的音乐集合
     * link :
     * latestReleaseTime : 1529110809000
     * videoNum : 535
     * adTrack : null
     * follow : {"itemType":"author","itemId":2163,"followed":false}
     * shield : {"itemType":"author","itemId":2163,"shielded":false}
     * approvedNotReadyVideoCount : 0
     * ifPgc : true
     */

    private int id;
    private String icon;
    private String name;
    private String description;
    private String link;
    private long latestReleaseTime;
    private int videoNum;
    private int adTrack;
    private Follow follow;
    private Shield shield;
    private int approvedNotReadyVideoCount;
    private boolean ifPgc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getLatestReleaseTime() {
        return latestReleaseTime;
    }

    public void setLatestReleaseTime(long latestReleaseTime) {
        this.latestReleaseTime = latestReleaseTime;
    }

    public int getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(int videoNum) {
        this.videoNum = videoNum;
    }

    public int getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(int adTrack) {
        this.adTrack = adTrack;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }

    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public int getApprovedNotReadyVideoCount() {
        return approvedNotReadyVideoCount;
    }

    public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
        this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
    }

    public boolean isIfPgc() {
        return ifPgc;
    }

    public void setIfPgc(boolean ifPgc) {
        this.ifPgc = ifPgc;
    }

}
