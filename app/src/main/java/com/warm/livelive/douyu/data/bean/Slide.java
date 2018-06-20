package com.warm.livelive.douyu.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-14 12:52
 * 描述：
 */
public class Slide {
    /**
     * cate_id : 350
     * title :
     * level : 1
     * resource : https://cs-op.douyucdn.cn/dypart/2018/06/08/556b4701597d1bf48d89427de9a89c2a.jpg
     * link_type : 1
     * link : https://v.douyu.com/show/85BAvq1OALBvG4Lm
     * is_room_show : 0
     */

    private int cate_id;
    private String title;
    private int level;
    private String resource;
    private int link_type;
    private String link;
    private int is_room_show;

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getLink_type() {
        return link_type;
    }

    public void setLink_type(int link_type) {
        this.link_type = link_type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIs_room_show() {
        return is_room_show;
    }

    public void setIs_room_show(int is_room_show) {
        this.is_room_show = is_room_show;
    }
}
