package com.warm.livelive.eyepetizer.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-17 04:53
 * 描述：
 */
public class Tag {

    /**
     * id : 14
     * name : 动画
     * actionUrl : eyepetizer://tag/14/?title=%E5%8A%A8%E7%94%BB
     * adTrack : null
     * desc : null
     * bgPicture : http://img.kaiyanapp.com/c4e5c0f76d21abbd23c9626af3c9f481.jpeg?imageMogr2/quality/100
     * headerImage : http://img.kaiyanapp.com/88da8548d31005032c37df4889d2104c.jpeg?imageMogr2/quality/100
     * tagRecType : IMPORTANT
     */

    private int id;
    private String name;
    private String actionUrl;
    private int adTrack;
    private String desc;
    private String bgPicture;
    private String headerImage;
    private String tagRecType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public int getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(int adTrack) {
        this.adTrack = adTrack;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getTagRecType() {
        return tagRecType;
    }

    public void setTagRecType(String tagRecType) {
        this.tagRecType = tagRecType;
    }
}
