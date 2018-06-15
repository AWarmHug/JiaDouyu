package com.warm.livelive.douyu.data.bean.douyu.live;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：warm
 * 时间：2018-06-12 10:54
 * 描述：
 */
public class LiveCate2 implements Parcelable {
    /**
     * cate2_id : 1
     * cate2_name : 英雄联盟
     * cate2_short_name : LOL
     * push_nearby : 0
     * is_vertical : 0
     * icon_url : https://cs-op.douyucdn.cn/dycatr/game_cate/d3e0073bfb714186ab0c818744601963.jpg
     * small_icon_url : https://cs-op.douyucdn.cn/dycatr/game_cate/ffdc72ed97b50ad8011de9a779b8d83b.png
     * square_icon_url : https://cs-op.douyucdn.cn/dycatr/78f61a979f3f80a3b8d4f017dffd5944.png
     */

    private int cate2_id;
    private String cate2_name;
    private String cate2_short_name;
    private int push_nearby;
    private int is_vertical;
    private String icon_url;
    private String small_icon_url;
    private String square_icon_url;

    public int getCate2_id() {
        return cate2_id;
    }

    public void setCate2_id(int cate2_id) {
        this.cate2_id = cate2_id;
    }

    public String getCate2_name() {
        return cate2_name;
    }

    public void setCate2_name(String cate2_name) {
        this.cate2_name = cate2_name;
    }

    public String getCate2_short_name() {
        return cate2_short_name;
    }

    public void setCate2_short_name(String cate2_short_name) {
        this.cate2_short_name = cate2_short_name;
    }

    public int getPush_nearby() {
        return push_nearby;
    }

    public void setPush_nearby(int push_nearby) {
        this.push_nearby = push_nearby;
    }

    public int getIs_vertical() {
        return is_vertical;
    }

    public void setIs_vertical(int is_vertical) {
        this.is_vertical = is_vertical;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getSmall_icon_url() {
        return small_icon_url;
    }

    public void setSmall_icon_url(String small_icon_url) {
        this.small_icon_url = small_icon_url;
    }

    public String getSquare_icon_url() {
        return square_icon_url;
    }

    public void setSquare_icon_url(String square_icon_url) {
        this.square_icon_url = square_icon_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cate2_id);
        dest.writeString(this.cate2_name);
        dest.writeString(this.cate2_short_name);
        dest.writeInt(this.push_nearby);
        dest.writeInt(this.is_vertical);
        dest.writeString(this.icon_url);
        dest.writeString(this.small_icon_url);
        dest.writeString(this.square_icon_url);
    }

    public LiveCate2() {
    }

    protected LiveCate2(Parcel in) {
        this.cate2_id = in.readInt();
        this.cate2_name = in.readString();
        this.cate2_short_name = in.readString();
        this.push_nearby = in.readInt();
        this.is_vertical = in.readInt();
        this.icon_url = in.readString();
        this.small_icon_url = in.readString();
        this.square_icon_url = in.readString();
    }

    public static final Parcelable.Creator<LiveCate2> CREATOR = new Parcelable.Creator<LiveCate2>() {
        @Override
        public LiveCate2 createFromParcel(Parcel source) {
            return new LiveCate2(source);
        }

        @Override
        public LiveCate2[] newArray(int size) {
            return new LiveCate2[size];
        }
    };
}
