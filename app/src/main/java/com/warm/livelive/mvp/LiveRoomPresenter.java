package com.warm.livelive.mvp;

import android.util.Log;

import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.data.bean.LiveRoom;
import com.warm.livelive.data.bean.RtmpUrl;
import com.warm.livelive.utils.rx.CustomObserver;
import com.warm.livelive.utils.rx.RxUtils;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * 作者：warm
 * 时间：2017-12-26 08:43
 * 描述：
 */

public class LiveRoomPresenter extends RxPresenter<LiveRoomContract.View>
        implements LiveRoomContract.Presenter {

    @Override
    public void getLiveRooms(String tagId, int page) {
        mDataManager
                .getLiveRooms(tagId, page)
                .compose(RxUtils.<List<LiveRoom>>ioToMain(mView))
                .subscribe(new CustomObserver<List<LiveRoom>>(mCompositeDisposable, mView) {
                    @Override
                    public void onNext(@NonNull List<LiveRoom> liveRooms) {
                        mView.getLiveRooms(liveRooms);
                    }
                });
    }

    @Override
    public void getLiveRtmpUrl(final String roomId) {
        mDataManager
                .getRtmpUrl(roomId)
                .compose(RxUtils.<RtmpUrl>ioToMain(mView))
                .subscribe(new CustomObserver<RtmpUrl>(mCompositeDisposable, mView) {
                    @Override
                    public void onNext(@NonNull RtmpUrl rtmpUrl) {
                        Log.d("&&&&&&", "onNext: "+rtmpUrl.toString());
//                        hlsUrl.setRoomId(roomId);
                        mView.getLiveRtmpUrl(rtmpUrl);
                    }
                });
    }


}
