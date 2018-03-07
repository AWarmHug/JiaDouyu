package com.warm.livelive.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：warm
 * 时间：2017-12-25 16:44
 * 描述：
 */

public class SubChannel implements Parcelable {


    /**
     * tag_id : 270
     * short_name : jdqs
     * tag_name : 绝地求生
     * tag_introduce : 《绝地求生》(PUBG) 是一款突破性的战术竞技类游戏，凭借其独特创新的玩法模式，写实风格带来的代入感和沉浸感深受玩家喜爱。
     * square_icon_name : {"web":"6b6fc9804611459df5354c713f7a34f8.jpg","mob":"29f0315728c7a9965b11552cbebe706e.jpg"}
     * pic_name : 63a6c37b1bece659805e58ffeac6d00a.jpg
     * pic_name2 : b0fc71c0c54944afca3eb0a350c30a93.jpg
     * icon_name : 5a92cff5881b5c62814f5289c79b38cf.jpg
     * small_icon_name : e44562661012d49e22a273c12ca176e3.jpg
     * orderdisplay : 3
     * rank_score : 0
     * night_rank_score : 0
     * nums : 0
     * push_ios : 1
     * push_home : 1
     * is_game_cate : 1
     * cate_id : 16
     * is_del : 0
     * is_relate : 1
     * push_vertical_screen : 0
     * push_nearby : 0
     * push_qqapp : 0
     * broadcast_limit : 3
     * vodd_cateids :
     * open_full_screen : 0
     * pic_url : https://staticlive.douyucdn.cn/upload/game_cate/63a6c37b1bece659805e58ffeac6d00a.jpg
     * url : /directory/game/jdqs
     * icon_url : https://staticlive.douyucdn.cn/upload/game_cate/5a92cff5881b5c62814f5289c79b38cf.jpg
     * small_icon_url : https://staticlive.douyucdn.cn/upload/game_cate/e44562661012d49e22a273c12ca176e3.jpg
     * count : 492
     * count_ios : 438
     * is_childs : 4
     */

    private String tag_id;
    private String short_name;
    private String tag_name;
    private String tag_introduce;
    private String square_icon_name;
    private String pic_name;
    private String pic_name2;
    private String icon_name;
    private String small_icon_name;
    private String orderdisplay;
    private String rank_score;
    private String nums;
    private String is_game_cate;
    private String cate_id;
    private String pic_url;
    private String url;
    private String icon_url;
    private String small_icon_url;
    private int count;
    private int count_ios;
    private int is_childs;

    public SubChannel() {
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getTag_introduce() {
        return tag_introduce;
    }

    public void setTag_introduce(String tag_introduce) {
        this.tag_introduce = tag_introduce;
    }

    public String getSquare_icon_name() {
        return square_icon_name;
    }

    public void setSquare_icon_name(String square_icon_name) {
        this.square_icon_name = square_icon_name;
    }

    public String getPic_name() {
        return pic_name;
    }

    public void setPic_name(String pic_name) {
        this.pic_name = pic_name;
    }

    public String getPic_name2() {
        return pic_name2;
    }

    public void setPic_name2(String pic_name2) {
        this.pic_name2 = pic_name2;
    }

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    public String getSmall_icon_name() {
        return small_icon_name;
    }

    public void setSmall_icon_name(String small_icon_name) {
        this.small_icon_name = small_icon_name;
    }

    public String getOrderdisplay() {
        return orderdisplay;
    }

    public void setOrderdisplay(String orderdisplay) {
        this.orderdisplay = orderdisplay;
    }

    public String getRank_score() {
        return rank_score;
    }

    public void setRank_score(String rank_score) {
        this.rank_score = rank_score;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getIs_game_cate() {
        return is_game_cate;
    }

    public void setIs_game_cate(String is_game_cate) {
        this.is_game_cate = is_game_cate;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount_ios() {
        return count_ios;
    }

    public void setCount_ios(int count_ios) {
        this.count_ios = count_ios;
    }

    public int getIs_childs() {
        return is_childs;
    }

    public void setIs_childs(int is_childs) {
        this.is_childs = is_childs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tag_id);
        dest.writeString(this.short_name);
        dest.writeString(this.tag_name);
        dest.writeString(this.tag_introduce);
        dest.writeString(this.square_icon_name);
        dest.writeString(this.pic_name);
        dest.writeString(this.pic_name2);
        dest.writeString(this.icon_name);
        dest.writeString(this.small_icon_name);
        dest.writeString(this.orderdisplay);
        dest.writeString(this.rank_score);
        dest.writeString(this.nums);
        dest.writeString(this.is_game_cate);
        dest.writeString(this.cate_id);
        dest.writeString(this.pic_url);
        dest.writeString(this.url);
        dest.writeString(this.icon_url);
        dest.writeString(this.small_icon_url);
        dest.writeInt(this.count);
        dest.writeInt(this.count_ios);
        dest.writeInt(this.is_childs);
    }

    protected SubChannel(Parcel in) {
        this.tag_id = in.readString();
        this.short_name = in.readString();
        this.tag_name = in.readString();
        this.tag_introduce = in.readString();
        this.square_icon_name = in.readString();
        this.pic_name = in.readString();
        this.pic_name2 = in.readString();
        this.icon_name = in.readString();
        this.small_icon_name = in.readString();
        this.orderdisplay = in.readString();
        this.rank_score = in.readString();
        this.nums = in.readString();
        this.is_game_cate = in.readString();
        this.cate_id = in.readString();
        this.pic_url = in.readString();
        this.url = in.readString();
        this.icon_url = in.readString();
        this.small_icon_url = in.readString();
        this.count = in.readInt();
        this.count_ios = in.readInt();
        this.is_childs = in.readInt();
    }

    public static final Creator<SubChannel> CREATOR = new Creator<SubChannel>() {
        @Override
        public SubChannel createFromParcel(Parcel source) {
            return new SubChannel(source);
        }

        @Override
        public SubChannel[] newArray(int size) {
            return new SubChannel[size];
        }
    };
}
