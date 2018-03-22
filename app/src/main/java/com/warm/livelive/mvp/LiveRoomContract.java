package com.warm.livelive.mvp;

import com.warm.livelive.base.BaseView;
import com.warm.livelive.base.IBasePresenter;
import com.warm.livelive.data.bean.LiveRoom;
import com.warm.livelive.data.bean.RtmpUrl;

import java.util.List;

/**
 * 作者：warm
 * 时间：2017-12-26 08:44
 * 描述：
 */

public interface LiveRoomContract {

    interface View extends BaseView{
        void getLiveRooms(List<LiveRoom> liveRooms);
        void getLiveRtmpUrl(RtmpUrl rtmpUrl);


    }

    interface Presenter extends IBasePresenter<View> {

        void getLiveRooms(String tagId,int page);

        void getLiveRtmpUrl(String roomId);

    }

}
