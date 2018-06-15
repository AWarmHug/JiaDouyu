package com.warm.livelive.douyu.data.bean.douyu;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * 作者：warm
 * 时间：2018-06-11 13:47
 * 描述：
 */
public class TabCate1 implements Parcelable {

    public static TabCate1 SPORT=getSport();

    private static TabCate1 getSport(){
        TabCate1 cate1=new TabCate1();
        cate1.setLevel(0);
        cate1.setTab_id(-3);
        cate1.setCate_name("体育直播");
        cate1.setCate_id(-3);
        cate1.setShort_name("tyzb");
        return cate1;
    }

    public static TabCate1 TYPE=getType();

    private static TabCate1 getType(){
        TabCate1 cate1=new TabCate1();
        cate1.setLevel(0);
        cate1.setTab_id(-2);
        cate1.setCate_name("分类");
        cate1.setCate_id(-2);
        cate1.setShort_name("fl");
        return cate1;
    }

    public static TabCate1 ALL=getAll();

    private static TabCate1 getAll(){
        TabCate1 cate1=new TabCate1();
        cate1.setLevel(0);
        cate1.setTab_id(0);
        cate1.setCate_name("全部");
        cate1.setCate_id(0);
        cate1.setShort_name("qb");
        return cate1;
    }


    /**
     * level : 2
     * tab_id : 29
     * cate_name : 刺激战场
     * cate_id : 350
     * push_nearby : 0
     * short_name : jdqscjzc
     * push_vertical_screen : 0
     * push_ios : 1
     */

    private int level;
    private int tab_id;
    private String cate_name;
    private int cate_id;
    private String push_nearby;
    private String short_name;
    private String push_vertical_screen;
    private String push_ios;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTab_id() {
        return tab_id;
    }

    public void setTab_id(int tab_id) {
        this.tab_id = tab_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getPush_nearby() {
        return push_nearby;
    }

    public void setPush_nearby(String push_nearby) {
        this.push_nearby = push_nearby;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.level);
        dest.writeInt(this.tab_id);
        dest.writeString(this.cate_name);
        dest.writeInt(this.cate_id);
        dest.writeString(this.push_nearby);
        dest.writeString(this.short_name);
        dest.writeString(this.push_vertical_screen);
        dest.writeString(this.push_ios);
    }

    public TabCate1() {
    }

    protected TabCate1(Parcel in) {
        this.level = in.readInt();
        this.tab_id = in.readInt();
        this.cate_name = in.readString();
        this.cate_id = in.readInt();
        this.push_nearby = in.readString();
        this.short_name = in.readString();
        this.push_vertical_screen = in.readString();
        this.push_ios = in.readString();
    }

    public static final Parcelable.Creator<TabCate1> CREATOR = new Parcelable.Creator<TabCate1>() {
        @Override
        public TabCate1 createFromParcel(Parcel source) {
            return new TabCate1(source);
        }

        @Override
        public TabCate1[] newArray(int size) {
            return new TabCate1[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabCate1 tabCate1 = (TabCate1) o;
        return level == tabCate1.level &&
                tab_id == tabCate1.tab_id &&
                cate_id == tabCate1.cate_id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(level, tab_id, cate_id);
    }
}
