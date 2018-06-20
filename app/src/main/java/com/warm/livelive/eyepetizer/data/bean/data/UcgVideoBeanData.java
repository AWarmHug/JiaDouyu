package com.warm.livelive.eyepetizer.data.bean.data;

import com.warm.livelive.eyepetizer.data.bean.Consumption;
import com.warm.livelive.eyepetizer.data.bean.Cover;
import com.warm.livelive.eyepetizer.data.bean.Owner;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-19 12:09
 * 描述：
 */
public class UcgVideoBeanData extends BaseData{

    /**
     * id : 1012
     * title :
     * description : 卢卡库进球瞬间
     * library : DAILY
     * tags : []
     * consumption : {"collectionCount":6,"shareCount":0,"replyCount":1}
     * resourceType : ugc_video
     * uid : 14873011
     * createTime : 1529350684000
     * updateTime : 1529374832000
     * collected : false
     * owner : {}
     * selectedTime : 1529374832000
     * checkStatus : CHECKED
     * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=1012&resourceType=ugc_video&editionType=default&source=aliyun
     * cover : {}
     * status : ONLINE
     * releaseTime : 1529350684000
     * duration : 15
     * transId : null
     * type : COMMON
     */

    private int id;
    private String title;
    private String description;
    private String library;
    private Consumption consumption;
    private String resourceType;
    private int uid;
    private long createTime;
    private long updateTime;
    private boolean collected;
    private Owner owner;
    private long selectedTime;
    private String checkStatus;
    private String playUrl;
    private Cover cover;
    private String status;
    private long releaseTime;
    private int duration;
    private Object transId;
    private String type;
    private List<?> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public long getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(long selectedTime) {
        this.selectedTime = selectedTime;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Object getTransId() {
        return transId;
    }

    public void setTransId(Object transId) {
        this.transId = transId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }


}
