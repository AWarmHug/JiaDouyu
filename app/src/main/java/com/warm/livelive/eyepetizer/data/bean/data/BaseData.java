package com.warm.livelive.eyepetizer.data.bean.data;

/**
 * 作者：warm
 * 时间：2018-06-17 15:33
 * 描述：
 */
public class BaseData {

    /**
     * dataType : FollowCard
     * header : {}
     * content : {}
     * adTrack : null
     */
    public static final String DATA_TYPE_FOLLOW_CARD ="FollowCard";
    public static final String DATA_TYPE_ITEM_COLLECTION ="ItemCollection";
    public static final String DATA_TYPE_TEXT_CARD ="TextCard";
    public static final String DATA_TYPE_UGC_PICTURE_BEAN ="UgcPictureBean";

    public static final String DATA_TYPE_UGC_VIDEO_BEAN ="UgcVideoBean";

    public static final String DATA_TYPE_VIDEO_BEAN_FOR_CLIENT ="VideoBeanForClient";

    private String dataType;
    private String adTrack;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }
}
