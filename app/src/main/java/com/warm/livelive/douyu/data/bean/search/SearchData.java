package com.warm.livelive.douyu.data.bean.search;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-21 16:34
 * 描述：
 */
public class SearchData {

    private List<Room> room;
    private List<Anchor> anchor;
    private List<Cate> cate;
    private List<Yuba> yuba;
    private List<Video> video;
    private List<Club> club;
    private int total;

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    public List<Anchor> getAnchor() {
        return anchor;
    }

    public void setAnchor(List<Anchor> anchor) {
        this.anchor = anchor;
    }

    public List<Cate> getCate() {
        return cate;
    }

    public void setCate(List<Cate> cate) {
        this.cate = cate;
    }

    public List<Yuba> getYuba() {
        return yuba;
    }

    public void setYuba(List<Yuba> yuba) {
        this.yuba = yuba;
    }

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }

    public List<Club> getClub() {
        return club;
    }

    public void setClub(List<Club> club) {
        this.club = club;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
