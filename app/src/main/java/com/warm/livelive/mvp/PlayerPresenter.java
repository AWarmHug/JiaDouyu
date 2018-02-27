package com.warm.livelive.mvp;

import com.warm.livelive.LiveApp;
import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.data.socket.netty.Douyu;

/**
 * 作者：warm
 * 时间：2017-12-27 13:04
 * 描述：
 */

public class PlayerPresenter extends RxPresenter<PlayerContract.View> implements PlayerContract.Presenter {
    private static final String TAG = "PlayerPresenter";


    public PlayerPresenter() {
    }

    @Override
    public void loadPrepare(String roomId, String groupId) {
        LiveApp.getInstance().getPushService().send(Douyu.getInstance().loadRoom(roomId));
    }

    @Override
    public void detach() {
        super.detach();
//        danmuSocket.loginOut();
//        danmuSocket.release();
    }
}
