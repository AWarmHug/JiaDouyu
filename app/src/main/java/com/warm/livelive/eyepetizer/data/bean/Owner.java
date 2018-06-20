package com.warm.livelive.eyepetizer.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-18 16:02
 * 描述：
 */
public class Owner {

    /**
     * uid : 300348215
     * nickname : 小光
     * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/WxLKJlXCibwqMrvskavocupP4Y3Ntp04CW7hR4Gj1uibQek74uONC3yRdATUPUC72Bt3D3E9piaaiaarxMINfCstVQ/132
     * userType : NORMAL
     * ifPgc : false
     * description : null
     * area : null
     * gender : null
     * registDate : 1484701385000
     * releaseDate : 1529226204000
     * cover : null
     * actionUrl : eyepetizer://pgc/detail/300348215/?title=%E5%B0%8F%E5%85%89&userType=NORMAL&tabIndex=0
     * followed : false
     * limitVideoOpen : true
     * library : BLOCK
     */

    private int uid;
    private String nickname;
    private String avatar;
    private String userType;
    private boolean ifPgc;
    private String description;
    private String area;
    private String gender;
    private long registDate;
    private long releaseDate;
    private String cover;
    private String actionUrl;
    private boolean followed;
    private boolean limitVideoOpen;
    private String library;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isIfPgc() {
        return ifPgc;
    }

    public void setIfPgc(boolean ifPgc) {
        this.ifPgc = ifPgc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getRegistDate() {
        return registDate;
    }

    public void setRegistDate(long registDate) {
        this.registDate = registDate;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public boolean isLimitVideoOpen() {
        return limitVideoOpen;
    }

    public void setLimitVideoOpen(boolean limitVideoOpen) {
        this.limitVideoOpen = limitVideoOpen;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }
}
