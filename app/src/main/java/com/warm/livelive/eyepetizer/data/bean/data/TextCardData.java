package com.warm.livelive.eyepetizer.data.bean.data;

import com.warm.livelive.eyepetizer.data.bean.Follow;

/**
 * 作者：warm
 * 时间：2018-06-17 15:36
 * 描述：
 */
public class TextCardData extends BaseData {

    /**
     * id : 0
     * type : header5
     * text : 社区精选
     * subTitle : null
     * actionUrl : null
     * adTrack : null
     * follow : null
     */

    private int id;
    private String type;
    private String text;
    private String subTitle;
    private String actionUrl;
    private Follow follow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
}
