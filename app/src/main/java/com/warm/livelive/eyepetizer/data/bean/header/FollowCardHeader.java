package com.warm.livelive.eyepetizer.data.bean.header;

/**
 * 作者：warm
 * 时间：2018-06-17 04:35
 * 描述：
 */
public class FollowCardHeader extends BaseHeader {


    /**
     * font : null
     * subTitle : null
     * subTitleFont : null
     * cover : null
     * label : null
     * labelList : null
     * icon : http://img.kaiyanapp.com/22192a40de238fe853b992ed57f1f098.jpeg
     * iconType : round
     * description : 科幻外衣下的爱情内核
     * time : 1528712855000
     * showHateVideo : false
     */

    private String icon;
    private String iconType;
    private String description;
    private long time;
    private boolean showHateVideo;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isShowHateVideo() {
        return showHateVideo;
    }

    public void setShowHateVideo(boolean showHateVideo) {
        this.showHateVideo = showHateVideo;
    }
}