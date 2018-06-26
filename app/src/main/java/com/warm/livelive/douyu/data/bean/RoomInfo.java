package com.warm.livelive.douyu.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：warm
 * 时间：2018-06-22 15:18
 * 描述：
 */
public class RoomInfo implements Parcelable {

    private int room_id;
    private String room_name;
    private int isVertical;
    private String roomSrc;

    public RoomInfo(int room_id, String room_name, int isVertical, String roomSrc) {
        this.room_id = room_id;
        this.room_name = room_name;
        this.isVertical = isVertical;
        this.roomSrc = roomSrc;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }


    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getIsVertical() {
        return isVertical;
    }

    public void setIsVertical(int isVertical) {
        this.isVertical = isVertical;
    }

    public String getRoomSrc() {
        return roomSrc;
    }

    public void setRoomSrc(String roomSrc) {
        this.roomSrc = roomSrc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.room_id);
        dest.writeString(this.room_name);
        dest.writeInt(this.isVertical);
        dest.writeString(this.roomSrc);
    }

    protected RoomInfo(Parcel in) {
        this.room_id = in.readInt();
        this.room_name = in.readString();
        this.isVertical = in.readInt();
        this.roomSrc = in.readString();
    }

    public static final Parcelable.Creator<RoomInfo> CREATOR = new Parcelable.Creator<RoomInfo>() {
        @Override
        public RoomInfo createFromParcel(Parcel source) {
            return new RoomInfo(source);
        }

        @Override
        public RoomInfo[] newArray(int size) {
            return new RoomInfo[size];
        }
    };
}
