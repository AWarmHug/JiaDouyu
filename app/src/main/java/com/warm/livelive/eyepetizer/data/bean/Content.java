package com.warm.livelive.eyepetizer.data.bean;

import com.warm.livelive.eyepetizer.data.bean.data.BaseData;

/**
 * 作者：warm
 * 时间：2018-06-17 15:15
 * 描述：
 */
public class Content {

    public static final String TYPE_SQUARE_CARD_COLLECTION = "squareCardCollection";
    public static final String TYPE_TEXT_CARD = "textCard";
    public static final String TYPE_PICTURE_FOLLOW_CARD = "pictureFollowCard";
    public static final String TYPE_FOLLOW_CARD = "followCard";
    public static final String TYPE_VIDEO_SMALL_CARD = "videoSmallCard";
    public static final String TYPE_UGC_PICTURE = "ugcPicture";
    public static final String TYPE_VIDEO = "video";


    /**
     * type : squareCardCollection
     * data : {}
     * tag : null
     * id : 0
     * adIndex : -1
     */

    private String type;
    private BaseData data;
    private String tag;
    private int id;
    private int adIndex;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BaseData getData() {
        return data;
    }

    public void setData(BaseData data) {
        this.data = data;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdIndex() {
        return adIndex;
    }

    public void setAdIndex(int adIndex) {
        this.adIndex = adIndex;
    }


}
