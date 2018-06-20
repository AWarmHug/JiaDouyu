package com.warm.livelive.douyu.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-14 13:05
 * 描述：
 */
public class Activity {
    /**
     * id : 904
     * cid2 : 181
     * sub_num : 1558
     * act_url :
     * act_button_text :
     * act_name : 王者荣耀季后赛巅峰对决
     * act_info : 19:00  GK vs JC
     * act_start_time : 1528369200
     * act_end_time : 1530961320
     * room_id : 1863767
     * is_vertical : 0
     * room_src : https://rpic.douyucdn.cn/amrpic-180614/1863767_1301.jpg
     * vertical_src : https://rpic.douyucdn.cn/amrpic-180614/1863767_1301.jpg
     * nrt : 0
     */

    private int id;
    private int cid2;
    private int sub_num;
    private String act_url;
    private String act_button_text;
    private String act_name;
    private String act_info;
    private long act_start_time;
    private long act_end_time;
    private long room_id;
    private int is_vertical;
    private String room_src;
    private String vertical_src;
    private String nrt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid2() {
        return cid2;
    }

    public void setCid2(int cid2) {
        this.cid2 = cid2;
    }

    public int getSub_num() {
        return sub_num;
    }

    public void setSub_num(int sub_num) {
        this.sub_num = sub_num;
    }

    public String getAct_url() {
        return act_url;
    }

    public void setAct_url(String act_url) {
        this.act_url = act_url;
    }

    public String getAct_button_text() {
        return act_button_text;
    }

    public void setAct_button_text(String act_button_text) {
        this.act_button_text = act_button_text;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAct_info() {
        return act_info;
    }

    public void setAct_info(String act_info) {
        this.act_info = act_info;
    }

    public long getAct_start_time() {
        return act_start_time;
    }

    public void setAct_start_time(long act_start_time) {
        this.act_start_time = act_start_time;
    }

    public long getAct_end_time() {
        return act_end_time;
    }

    public void setAct_end_time(long act_end_time) {
        this.act_end_time = act_end_time;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public int getIs_vertical() {
        return is_vertical;
    }

    public void setIs_vertical(int is_vertical) {
        this.is_vertical = is_vertical;
    }

    public String getRoom_src() {
        return room_src;
    }

    public void setRoom_src(String room_src) {
        this.room_src = room_src;
    }

    public String getVertical_src() {
        return vertical_src;
    }

    public void setVertical_src(String vertical_src) {
        this.vertical_src = vertical_src;
    }

    public String getNrt() {
        return nrt;
    }

    public void setNrt(String nrt) {
        this.nrt = nrt;
    }
}
