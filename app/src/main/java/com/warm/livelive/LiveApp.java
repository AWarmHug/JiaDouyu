package com.warm.livelive;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.warm.livelive.douyu.data.DataManager;
import com.warm.livelive.douyu.data.socket.netty.PushService;

/**
 * 作者：warm
 * 时间：2017-12-25 14:48
 * 描述：
 */

public class LiveApp extends Application {


    private static LiveApp instance;

    private DataManager mDataManager;

    private PushService pushService;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PushService.MyBinder binder = (PushService.MyBinder) service;
            pushService = binder.getService();
        }

        //client 和service连接意外丢失时，会调用该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mDataManager = DataManager.newInstance();
        Intent intent = new Intent(this, PushService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public static LiveApp getInstance() {
        return instance;
    }

    public PushService getPushService() {
        return pushService;
    }


    public DataManager getDataManager() {
        return mDataManager;
    }
}
