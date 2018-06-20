package com.warm.livelive.douyu.data.socket.netty;

import com.warm.livelive.error.KnownException;

/**
 * 作者：warm
 * 时间：2018-02-27 12:33
 * 描述：
 */
public interface OnLoadListener {

    void onLoading();

    void onError(KnownException e);

    void onSuccess();

    void onMessage(String type,String msg);

}
