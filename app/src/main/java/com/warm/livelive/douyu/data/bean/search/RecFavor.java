package com.warm.livelive.douyu.data.bean.search;

/**
 * 作者：warm
 * 时间：2018-06-22 10:45
 * 描述：
 */
public class RecFavor {

    /**
     * roomId : 4468239
     * nickname : 瑞瑞阿emmm
     * anchorId : byARkNDMpR76
     * avatar : https://apic.douyucdn.cn/upload/avanew/face/201806/74cf20f617d27b84df7e0797958abbe5_middle.jpg
     * cate2Id : 201
     * cate2Name : 颜值
     * online : 6656
     * isLive : 1
     * roomSrc : https://rpic.douyucdn.cn/live-cover/appCovers/2018/05/11/4468239_20180511205319_small.jpg
     * isVertical : 1
     * anchorLevel : 30
     * followNum : 1673
     * ranktype : -133
     * recomType : 1003
     * rpos : 9
     * hn : 6656
     * nrt : 0
     */

    private int roomId;
    private String nickname;
    private String anchorId;
    private String avatar;
    private int cate2Id;
    private String cate2Name;
    private int online;
    private int isLive;
    private String roomSrc;
    private int isVertical;
    private int anchorLevel;
    private int followNum;
    private String ranktype;
    private String recomType;
    private String rpos;
    private int hn;
    private int nrt;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAnchorId() {
        return anchorId;
    }

    public void setAnchorId(String anchorId) {
        this.anchorId = anchorId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getCate2Id() {
        return cate2Id;
    }

    public void setCate2Id(int cate2Id) {
        this.cate2Id = cate2Id;
    }

    public String getCate2Name() {
        return cate2Name;
    }

    public void setCate2Name(String cate2Name) {
        this.cate2Name = cate2Name;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getIsLive() {
        return isLive;
    }

    public void setIsLive(int isLive) {
        this.isLive = isLive;
    }

    public String getRoomSrc() {
        return roomSrc;
    }

    public void setRoomSrc(String roomSrc) {
        this.roomSrc = roomSrc;
    }

    public int getIsVertical() {
        return isVertical;
    }

    public void setIsVertical(int isVertical) {
        this.isVertical = isVertical;
    }

    public int getAnchorLevel() {
        return anchorLevel;
    }

    public void setAnchorLevel(int anchorLevel) {
        this.anchorLevel = anchorLevel;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public String getRanktype() {
        return ranktype;
    }

    public void setRanktype(String ranktype) {
        this.ranktype = ranktype;
    }

    public String getRecomType() {
        return recomType;
    }

    public void setRecomType(String recomType) {
        this.recomType = recomType;
    }

    public String getRpos() {
        return rpos;
    }

    public void setRpos(String rpos) {
        this.rpos = rpos;
    }

    public int getHn() {
        return hn;
    }

    public void setHn(int hn) {
        this.hn = hn;
    }

    public int getNrt() {
        return nrt;
    }

    public void setNrt(int nrt) {
        this.nrt = nrt;
    }

    public String getRoomName(){
        return nickname+"的直播间";
    }
}
