package com.warm.livelive.data.socket.netty;

import com.warm.livelive.error.CustomException;

/**
 * 作者：warm
 * 时间：2018-02-27 12:33
 * 描述：
 */
public interface OnHandlerListener {
    void onError(CustomException e);


    void onDanmu(String type, String msg);


}
