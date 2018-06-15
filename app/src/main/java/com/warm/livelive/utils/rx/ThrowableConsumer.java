package com.warm.livelive.utils.rx;

import android.content.Intent;
import android.provider.Settings;

import com.warm.livelive.base.BaseView;
import com.warm.livelive.error.KnownException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * 作者：warm
 * 时间：2018-06-12 10:19
 * 描述：
 */
public class ThrowableConsumer implements Consumer<Throwable> {
    private BaseView mView;

    public ThrowableConsumer(BaseView view) {
        this.mView = view;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
        if (mView != null) {
            mView.dismissEmptyLoad();
            if (throwable instanceof KnownException) {
                customError((KnownException) throwable);
            } else if (throwable instanceof UnknownHostException) {
                handle(new KnownException(KnownException.HTTP_ERROR, "网络连接异常，查看网络情况", "去设置", new KnownException.OnErrorListener() {
                    @Override
                    public void errorAction() {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        mView.getBVActivity().startActivity(intent);
                    }
                }));
            } else if (throwable instanceof HttpException) {
                handle(new KnownException(KnownException.HTTP_ERROR, "网络出错，重新加载试试"));
            } else if (throwable instanceof ConnectException) {
                handle(new KnownException(KnownException.HTTP_ERROR, "网络出错，请检查网络是否可用"));
            } else if (throwable instanceof SocketTimeoutException) {
                handle(new KnownException(KnownException.HTTP_ERROR, "网络请求超时，请查看网络状况"));
            } else {
                unknownError(new KnownException(KnownException.UNKONW, "未知错误，重新加载试试"));
            }
        }
    }


    public void handle(@NonNull KnownException exception) {
        mView.toast(exception);
    }

    public void unknownError(@NonNull KnownException exception) {
        handle(exception);
    }

    public void customError(@NonNull KnownException exception) {
        handle(exception);
    }
}
