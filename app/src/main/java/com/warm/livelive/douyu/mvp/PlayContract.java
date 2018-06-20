package com.warm.livelive.douyu.mvp;

import com.warm.livelive.base.BasePresenter;
import com.warm.livelive.base.BaseView;
import com.warm.livelive.douyu.data.bean.douyu.RtmpUrl;
import com.warm.livelive.douyu.data.bean.douyu.live.LiveRoomItem;

/**
 * 作者：warm
 * 时间：2018-06-19 17:41
 * 描述：
 */
public interface PlayContract {

    interface Presenter extends BasePresenter<View>{
        void playPrepare(LiveRoomItem item);

        void loadDanmu(RtmpUrl rtmpUrl);
    }

    interface View extends BaseView{
        void playPrepared(RtmpUrl rtmpUrl);

        void showDanmu(String type, String msg);
    }
}
