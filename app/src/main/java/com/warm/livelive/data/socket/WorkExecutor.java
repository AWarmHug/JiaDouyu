package com.warm.livelive.data.socket;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：warm
 * 时间：2017-10-16 17:07
 * 描述：
 */

public class WorkExecutor {
    private static WorkExecutor workExecutor = new WorkExecutor();

    private ExecutorService mExecutorService;


    private WorkExecutor() {
    }

    public static WorkExecutor getInstance() {
        return workExecutor;
    }

    public void runWorker(Runnable runnable) {
        checkWorkExecutor();
        mExecutorService.execute(runnable);
    }




    public void runUi(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(runnable);
        }
    }


    private void checkWorkExecutor() {
        if (mExecutorService == null) {
            mExecutorService = Executors.newCachedThreadPool();
        }
    }


}
