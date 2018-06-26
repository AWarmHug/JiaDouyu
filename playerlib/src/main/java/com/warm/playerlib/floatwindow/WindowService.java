package com.warm.playerlib.floatwindow;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 作者：warm
 * 时间：2018-06-23 19:50
 * 描述：
 */
public class WindowService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
