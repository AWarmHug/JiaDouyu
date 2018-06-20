package com.warm.livelive.eyepetizer.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-19 12:04
 * 描述：
 */
public class Label {

    /**
     * text : 360°全景
     * card : 360°全景
     * detail : 360°全景
     */

    private String text;
    private String card;
    private String detail;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
