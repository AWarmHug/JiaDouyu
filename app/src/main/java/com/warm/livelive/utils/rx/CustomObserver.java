package com.warm.livelive.utils.rx;

import android.content.Intent;
import android.provider.Settings;

import com.warm.livelive.base.BaseView;
import com.warm.livelive.error.CustomException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 作者：warm
 * 时间：2017-12-12 15:17
 * 描述：
 */

public abstract class CustomObserver<T> implements Observer<T> {
    private CompositeDisposable compositeDisposable;
    private BaseView mView;


    public CustomObserver(CompositeDisposable compositeDisposable, BaseView mView) {
        this.compositeDisposable = compositeDisposable;
        this.mView = mView;
    }

    @Override
    public final void onSubscribe(@NonNull Disposable d) {
        compositeDisposable.add(d);
    }


    @Override
    public void onComplete() {

    }

    @Override
    public final void onError(@NonNull Throwable throwable) {
        if (mView != null) {
            mView.onDismissLoad();
            if (throwable instanceof CustomException) {
                onCustomError((CustomException) throwable);
            } else if (throwable instanceof UnknownHostException) {
                handle(new CustomException(CustomException.HTTP_ERROR, "网络连接异常，查看网络情况", "去设置", new CustomException.OnErrorListener() {
                    @Override
                    public void errorAction() {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        mView.getBVActivity().startActivity(intent);
                    }
                }));
            } else if (throwable instanceof HttpException) {
                handle(new CustomException(CustomException.HTTP_ERROR, "网络出错，重新加载试试"));
            } else if (throwable instanceof ConnectException) {
                handle(new CustomException(CustomException.HTTP_ERROR, "网络出错，请检查网络是否可用"));
            } else if (throwable instanceof SocketTimeoutException) {
                handle(new CustomException(CustomException.HTTP_ERROR, "网络请求超时，请查看网络状况"));
            } else {
                onUnknowError(new CustomException(CustomException.UNKONW, "未知错误，重新加载试试"));
            }
        }
    }

    private void handle(@NonNull CustomException exception) {
        mView.onTakeException(exception);
    }

    public void onUnknowError(@NonNull CustomException exception){
        handle(exception);
    }

    public void onCustomError(@NonNull CustomException exception){
        handle(exception);
    }

}
