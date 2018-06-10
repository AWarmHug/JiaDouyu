package com.warm.livelive.douyu.mvp;

import com.warm.livelive.base.IBasePresenter;
import com.warm.livelive.base.BaseView;
import com.warm.livelive.douyu.data.bean.HlsUrl;
import com.warm.livelive.douyu.data.bean.LiveRoom;

import java.util.List;

/**
 * 作者：warm
 * 时间：2017-12-26 08:44
 * 描述：
 */

public interface LiveRoomContract {

    interface View extends BaseView{
        void getLiveRooms(List<LiveRoom> liveRooms);
        void getLiveHlsUrl(HlsUrl hlsUrl);


    }

    interface Presenter extends IBasePresenter<View> {

        void getLiveRooms(String tagId,int page);

        void getLiveHlsUrl(String roomId);

    }

}
