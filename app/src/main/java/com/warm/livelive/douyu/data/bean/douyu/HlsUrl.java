package com.warm.livelive.douyu.data.bean.douyu;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：warm
 * 时间：2017-12-26 16:46
 * 描述：
 */

public class HlsUrl implements Parcelable {

    /**
     * hls_url : http://hlsa.douyucdn.cn/live/78561rZCNIVltVrM_550/playlist.m3u8?wsSecret=ab8283eb5ed1d2bc02912a96042a6702&wsTime=1514274482&token=h5-douyu-0-78561-4adbbb8013551cc6add77cfc4dec388b&did=h5_did
     */

    private String roomId;
    private String hls_url;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHls_url() {
        return hls_url;
    }

    public void setHls_url(String hls_url) {
        this.hls_url = hls_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.roomId);
        dest.writeString(this.hls_url);
    }

    public HlsUrl() {
    }

    protected HlsUrl(Parcel in) {
        this.roomId = in.readString();
        this.hls_url = in.readString();
    }

    public static final Parcelable.Creator<HlsUrl> CREATOR = new Parcelable.Creator<HlsUrl>() {
        @Override
        public HlsUrl createFromParcel(Parcel source) {
            return new HlsUrl(source);
        }

        @Override
        public HlsUrl[] newArray(int size) {
            return new HlsUrl[size];
        }
    };
}
