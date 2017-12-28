package com.warm.livelive.mvp;

import android.util.Log;

import com.warm.livelive.base.RxPresenter;
import com.warm.livelive.data.socket.DanmuSocket;
import com.warm.livelive.error.CustomException;

/**
 * 作者：warm
 * 时间：2017-12-27 13:04
 * 描述：
 */

public class PlayerPresenter extends RxPresenter<PlayerContract.View> implements PlayerContract.Presenter {
    private static final String TAG = "PlayerPresenter";
    private DanmuSocket danmuSocket;

    public PlayerPresenter() {
        danmuSocket=DanmuSocket.getInstance();
    }

    @Override
    public void loadPrepare(String roomId, String groupId) {
        danmuSocket.loadAndAction(roomId, groupId, new DanmuSocket.OnDanmuListener() {
            @Override
            public void onError(CustomException e) {

            }

            @Override
            public void onLoadSuccess() {

            }

            @Override
            public void onDanmu(String dan) {
                Log.d(TAG, "onDanmu: "+ dan);
            }

            @Override
            public void onLoadOut() {

            }
        });
    }

    @Override
    public void detach() {
        super.detach();
        danmuSocket.loginOut();
        danmuSocket.release();
    }
}
