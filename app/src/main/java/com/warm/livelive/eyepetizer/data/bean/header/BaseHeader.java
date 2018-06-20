package com.warm.livelive.eyepetizer.data.bean.header;

import com.warm.livelive.eyepetizer.data.bean.Label;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-06-18 15:05
 * 描述：
 */
public class BaseHeader {

    /**
     * id : 5
     * title : 开眼今日编辑精选
     * font : bigBold
     * subTitle : MONDAY, JUNE 18
     * subTitleFont : lobster
     * textAlign : left
     * cover : null
     * label : null
     * actionUrl : eyepetizer://feed?tabIndex=2
     * labelList : null
     */

    private int id;
    private String title;
    private String font;
    private String subTitle;
    private String subTitleFont;
    private String textAlign;
    private Object cover;
    private Label label;
    private String actionUrl;
    private List<Label> labelList;

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

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitleFont() {
        return subTitleFont;
    }

    public void setSubTitleFont(String subTitleFont) {
        this.subTitleFont = subTitleFont;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public Object getCover() {
        return cover;
    }

    public void setCover(Object cover) {
        this.cover = cover;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }
}
