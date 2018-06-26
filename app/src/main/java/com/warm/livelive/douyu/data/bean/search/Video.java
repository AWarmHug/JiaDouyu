package com.warm.livelive.douyu.data.bean.search;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：warm
 * 时间：2018-06-21 16:28
 * 描述：
 */
public class Video {
    /**
     * point_id : 1841207
     * hash_id : jXqeO74mPxPMxywG
     * title : 英雄联盟S7总决赛SSG VS SKT第一场
     * cid2 : 5
     * nickname : 英雄联盟官方赛事
     * video_pic : https://cs-op.douyucdn.cn/vod-cover/2017/11/04/8555ed2fad83b3fe379f5540f098df43.jpg
     * is_vertical : 0
     * video_duration : 2429
     */

    private int point_id;
    private String hash_id;

    @SerializedName(value = "title", alternate = {"video_title"})
    private String title;
    private int cid2;
    private String nickname;
    @SerializedName(value = "video_pic", alternate = {"video_cover"})
    private String video_pic;
    private int is_vertical;
    private int video_duration;

    public int getPoint_id() {
        return point_id;
    }

    public void setPoint_id(int point_id) {
        this.point_id = point_id;
    }

    public String getHash_id() {
        return hash_id;
    }

    public void setHash_id(String hash_id) {
        this.hash_id = hash_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCid2() {
        return cid2;
    }

    public void setCid2(int cid2) {
        this.cid2 = cid2;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getVideo_pic() {
        return video_pic;
    }

    public void setVideo_pic(String video_pic) {
        this.video_pic = video_pic;
    }

    public int getIs_vertical() {
        return is_vertical;
    }

    public void setIs_vertical(int is_vertical) {
        this.is_vertical = is_vertical;
    }

    public int getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(int video_duration) {
        this.video_duration = video_duration;
    }
}
