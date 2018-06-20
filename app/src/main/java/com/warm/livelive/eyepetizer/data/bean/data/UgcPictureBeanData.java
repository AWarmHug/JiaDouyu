package com.warm.livelive.eyepetizer.data.bean.data;

import com.warm.livelive.eyepetizer.data.bean.Consumption;
import com.warm.livelive.eyepetizer.data.bean.Cover;
import com.warm.livelive.eyepetizer.data.bean.Owner;
import com.warm.livelive.eyepetizer.data.bean.Tag;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-18 15:59
 * 描述：
 */
public class UgcPictureBeanData extends BaseData {

    /**
     * id : 1299
     * title :
     * description : 我注意过，即使是那些声称“一切都是命中注定的，而且我们无力改变”的人，在过马路前都会左右看。by 史蒂芬·霍金
     * library : DAILY
     * tags : []
     * consumption : {}
     * resourceType : ugc_picture
     * uid : 300348215
     * createTime : 1529226136000
     * updateTime : 1529238345000
     * collected : false
     * owner : {}
     * selectedTime : 1529237690000
     * checkStatus : CHECKED
     * url : http://img.kaiyanapp.com/57226e7d01f3110444dd26a56557c32b.png?imageMogr2/quality/100!/format/jpg
     * cover : {}
     * status : 1
     * releaseTime : 1529226136000
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
    private String url;
    private Cover cover;
    private int status;
    private long releaseTime;
    private List<Tag> tags;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
