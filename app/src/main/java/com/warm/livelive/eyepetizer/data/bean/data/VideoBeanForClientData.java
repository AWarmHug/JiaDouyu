package com.warm.livelive.eyepetizer.data.bean.data;

import com.warm.livelive.eyepetizer.data.bean.Author;
import com.warm.livelive.eyepetizer.data.bean.Consumption;
import com.warm.livelive.eyepetizer.data.bean.Cover;
import com.warm.livelive.eyepetizer.data.bean.Label;
import com.warm.livelive.eyepetizer.data.bean.PlayInfo;
import com.warm.livelive.eyepetizer.data.bean.Provider;
import com.warm.livelive.eyepetizer.data.bean.Tag;
import com.warm.livelive.eyepetizer.data.bean.WebUrl;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-18 16:06
 * 描述：
 */
public class VideoBeanForClientData extends BaseData {

    /**
     * id : 1130
     * title : 世界本该如此：One Day
     * description : 生活中，不如意事常八九，但仔细想想，总有那么些人愿意倾听你的烦恼，并给予必要的帮助。对于那些帮助你的人，最好的回馈方式大概是让这种爱传递下去。今日奉上古早的一镜到底，感恩节，祝每个人都快乐。From geeze
     * library : DAILY
     * tags : []
     * consumption : {}
     * resourceType : video
     * slogan : null
     * provider : {}
     * category : 音乐
     * author : {}
     * cover : {}
     * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=1130&resourceType=video&editionType=default&source=aliyun
     * thumbPlayUrl : null
     * duration : 287
     * webUrl : {}
     * releaseTime : 1448467200000
     * playInfo : []
     * campaign : null
     * waterMarks : null
     * adTrack : null
     * type : NORMAL
     * titlePgc : null
     * descriptionPgc : null
     * remark : null
     * ifLimitVideo : false
     * searchWeight : 0
     * idx : 0
     * shareAdTrack : null
     * favoriteAdTrack : null
     * webAdTrack : null
     * date : 1448467200000
     * promotion : null
     * label : null
     * labelList : []
     * descriptionEditor : 生活中，不如意事常八九，但仔细想想，总有那么些人愿意倾听你的烦恼，并给予必要的帮助。对于那些帮助你的人，最好的回馈方式大概是让这种爱传递下去。今日奉上古早的一镜到底，感恩节，祝每个人都快乐。From geeze
     * collected : false
     * played : false
     * subtitles : []
     * lastViewTime : null
     * playlists : null
     * src : 7
     */

    private int id;
    private String title;
    private String description;
    private String library;
    private Consumption consumption;
    private String resourceType;
    private Object slogan;
    private Provider provider;
    private String category;
    private Author author;
    private Cover cover;
    private String playUrl;
    private Object thumbPlayUrl;
    private int duration;
    private WebUrl webUrl;
    private long releaseTime;
    private Object campaign;
    private Object waterMarks;
    private String type;
    private Object titlePgc;
    private Object descriptionPgc;
    private Object remark;
    private boolean ifLimitVideo;
    private int searchWeight;
    private int idx;
    private int shareAdTrack;
    private int favoriteAdTrack;
    private int webAdTrack;
    private long date;
    private Object promotion;
    private Label label;
    private String descriptionEditor;
    private boolean collected;
    private boolean played;
    private Object lastViewTime;
    private Object playlists;
    private int src;
    private List<Tag> tags;
    private List<PlayInfo> playInfo;
    private List<Label> labelList;
    private List<?> subtitles;

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

    public Object getSlogan() {
        return slogan;
    }

    public void setSlogan(Object slogan) {
        this.slogan = slogan;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Object getThumbPlayUrl() {
        return thumbPlayUrl;
    }

    public void setThumbPlayUrl(Object thumbPlayUrl) {
        this.thumbPlayUrl = thumbPlayUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public WebUrl getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(WebUrl webUrl) {
        this.webUrl = webUrl;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Object getCampaign() {
        return campaign;
    }

    public void setCampaign(Object campaign) {
        this.campaign = campaign;
    }

    public Object getWaterMarks() {
        return waterMarks;
    }

    public void setWaterMarks(Object waterMarks) {
        this.waterMarks = waterMarks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getTitlePgc() {
        return titlePgc;
    }

    public void setTitlePgc(Object titlePgc) {
        this.titlePgc = titlePgc;
    }

    public Object getDescriptionPgc() {
        return descriptionPgc;
    }

    public void setDescriptionPgc(Object descriptionPgc) {
        this.descriptionPgc = descriptionPgc;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public boolean isIfLimitVideo() {
        return ifLimitVideo;
    }

    public void setIfLimitVideo(boolean ifLimitVideo) {
        this.ifLimitVideo = ifLimitVideo;
    }

    public int getSearchWeight() {
        return searchWeight;
    }

    public void setSearchWeight(int searchWeight) {
        this.searchWeight = searchWeight;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getShareAdTrack() {
        return shareAdTrack;
    }

    public void setShareAdTrack(int shareAdTrack) {
        this.shareAdTrack = shareAdTrack;
    }

    public int getFavoriteAdTrack() {
        return favoriteAdTrack;
    }

    public void setFavoriteAdTrack(int favoriteAdTrack) {
        this.favoriteAdTrack = favoriteAdTrack;
    }

    public int getWebAdTrack() {
        return webAdTrack;
    }

    public void setWebAdTrack(int webAdTrack) {
        this.webAdTrack = webAdTrack;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getDescriptionEditor() {
        return descriptionEditor;
    }

    public void setDescriptionEditor(String descriptionEditor) {
        this.descriptionEditor = descriptionEditor;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public Object getLastViewTime() {
        return lastViewTime;
    }

    public void setLastViewTime(Object lastViewTime) {
        this.lastViewTime = lastViewTime;
    }

    public Object getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Object playlists) {
        this.playlists = playlists;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<PlayInfo> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<PlayInfo> playInfo) {
        this.playInfo = playInfo;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }

    public List<?> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<?> subtitles) {
        this.subtitles = subtitles;
    }

}
