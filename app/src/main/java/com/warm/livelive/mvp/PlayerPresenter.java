package com.warm.livelive.mvp;

import com.warm.livelive.LiveApp;
import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.data.socket.netty.Douyu;
import com.warm.livelive.event.Danmu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 作者：warm
 * 时间：2017-12-27 13:04
 * 描述：
 */

public class PlayerPresenter extends RxPresenter<PlayerContract.View> implements PlayerContract.Presenter {
    private static final String TAG = "PlayerPresenter";


    public PlayerPresenter() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void loadPrepare(String roomId, String groupId) {
        LiveApp.getInstance().getPushService().send(Douyu.getInstance().loadRoom(roomId));
        LiveApp.getInstance().getPushService().send(Douyu.getInstance().loadGroup(roomId,"-9999"));
    }

    @Subscribe
    public void onDanmuEvent(Danmu danmu) {
        mView.getDanmu(danmu.getDanmu());

    }

    @Override
    public void detach() {
        super.detach();
        LiveApp.getInstance().getPushService().send(Douyu.getInstance().loginOut());
        EventBus.getDefault().unregister(this);
//        danmuSocket.loginOut();
//        danmuSocket.release();
    }
}
