package com.warm.livelive.douyu.data.socket.netty;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.warm.livelive.error.KnownException;
import com.warm.livelive.event.Danmu;
import com.warm.livelive.utils.rx.RxUtils;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * 作者：warm
 * 时间：2018-02-26 16:41
 * 描述：
 */
public class PushService extends Service {


    public class MyBinder extends Binder {

        public PushService getService() {
            return PushService.this;
        }
    }

    //通过binder实现了 调用者（client）与 service之间的通信
    private MyBinder binder = new MyBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Observable
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        NettyClient.getInstance().startConnect(new OnHandlerListener() {
                            @Override
                            public void onError(KnownException e) {

                            }

                            @Override
                            public void onDanmu(String type, String msg) {
//                                Log.d("Netty", "onDanmu: " + msg);
                                EventBus.getDefault().post(new Danmu(msg));
                            }
                        });
                    }
                })
                .compose(RxUtils.ioToMain())
                .subscribe();

        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    public void send(String msg) {

        NettyClient.getInstance().send(msg);
    }

}
