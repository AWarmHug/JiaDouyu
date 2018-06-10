package com.warm.livelive.douyu.mvp;

import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.douyu.data.bean.HlsUrl;
import com.warm.livelive.douyu.data.bean.LiveRoom;
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
    public void getLiveHlsUrl(final String roomId) {
        mDataManager
                .getHlsUrl(roomId)
                .compose(RxUtils.<HlsUrl>ioToMain(mView))
                .subscribe(new CustomObserver<HlsUrl>(mCompositeDisposable, mView) {
                    @Override
                    public void onNext(@NonNull HlsUrl hlsUrl) {
                        hlsUrl.setRoomId(roomId);
                        mView.getLiveHlsUrl(hlsUrl);
                    }
                });
    }


}
