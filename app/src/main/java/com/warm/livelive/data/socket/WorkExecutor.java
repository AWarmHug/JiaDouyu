package com.warm.livelive.data.socket;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 作者：warm
 * 时间：2017-10-16 17:07
 * 描述：
 */

public class WorkExecutor {
    private static WorkExecutor workExecutor = new WorkExecutor();

    private ExecutorService mExecutorService;
    private ScheduledExecutorService scheduledExecutorService;


    private WorkExecutor() {
    }

    public static WorkExecutor getInstance() {
        return workExecutor;
    }

    public void runWorker(Runnable runnable) {
        checkWorkExecutor();
        mExecutorService.execute(runnable);
    }

    public void runWorkerSchedule(Runnable runnable, int time) {
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            scheduledExecutorService = Executors.newScheduledThreadPool(1);
        }
        scheduledExecutorService.scheduleWithFixedDelay(runnable, 0, time, TimeUnit.MILLISECONDS);

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
        if (mExecutorService == null || mExecutorService.isShutdown()) {
            mExecutorService = Executors.newCachedThreadPool();
        }
    }


    public void release() {
        if (mExecutorService != null) {
            _release(mExecutorService);
        }
        if (scheduledExecutorService != null) {
            _release(scheduledExecutorService);
        }
    }

    private void _release(ExecutorService pool) {
        pool.shutdown();
        if (!pool.isTerminated()) {
            pool.shutdownNow();
        }

    }
}
