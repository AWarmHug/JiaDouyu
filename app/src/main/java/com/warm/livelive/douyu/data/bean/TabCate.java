package com.warm.livelive.douyu.data.bean;

/**
 * 作者：warm
 * 时间：2018-03-07 10:04
 * 描述：
 */
public class TabCate {

    /**
     * level : 1
     * cate_id : 1
     * cate_name : 网游竞技
     * short_name : PCgame
     * push_nearby : 0
     * push_vertical_screen : 0
     * push_ios : 1
     * push_show : 0
     * hasSpecialCate : 0
     */

    private int level;
    private int cate_id;
    private String cate_name;
    private String short_name;
    private String push_nearby;
    private String push_vertical_screen;
    private String push_ios;
    private String push_show;
    private int hasSpecialCate;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getPush_nearby() {
        return push_nearby;
    }

    public void setPush_nearby(String push_nearby) {
        this.push_nearby = push_nearby;
    }

    public String getPush_vertical_screen() {
        return push_vertical_screen;
    }

    public void setPush_vertical_screen(String push_vertical_screen) {
        this.push_vertical_screen = push_vertical_screen;
    }

    public String getPush_ios() {
        return push_ios;
    }

    public void setPush_ios(String push_ios) {
        this.push_ios = push_ios;
    }

    public String getPush_show() {
        return push_show;
    }

    public void setPush_show(String push_show) {
        this.push_show = push_show;
    }

    public int getHasSpecialCate() {
        return hasSpecialCate;
    }

    public void setHasSpecialCate(int hasSpecialCate) {
        this.hasSpecialCate = hasSpecialCate;
    }
}
