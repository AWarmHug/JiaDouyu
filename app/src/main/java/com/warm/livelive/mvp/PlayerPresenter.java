package com.warm.livelive.mvp;

import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.data.socket.DanmuSocket;

/**
 * 作者：warm
 * 时间：2017-12-27 13:04
 * 描述：
 */

public class PlayerPresenter extends RxPresenter<PlayerContract.View> implements PlayerContract.Presenter {
    private DanmuSocket danmuSocket;

    public PlayerPresenter() {
        danmuSocket=DanmuSocket.getInstance();
    }

    @Override
    public void loadPrepare(String roomId, String groupId) {
        danmuSocket.prepare(roomId,groupId);



    }
}
