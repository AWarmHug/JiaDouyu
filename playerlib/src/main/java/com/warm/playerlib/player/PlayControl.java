package com.warm.playerlib.player;

/**
 * 作者：warm
 * 时间：2018-05-15 17:44
 * 描述：所有可以设置的操作
 */
public interface PlayControl {

    boolean isPlaying();

    void start();

    void pauseUser();

    void seekTo(long seekTo);

    boolean isFull();

    void toFull();

    void toNotFull();

    long getCurrentPosition();

    long getDuration();

    void setScaleType(int scaleType);

    void replay();
}
