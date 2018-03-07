package com.warm.livelive.data.socket.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 作者：warm
 * 时间：2018-02-26 16:56
 * 描述：
 */
public class DanmuChannelInitializer extends ChannelInitializer<SocketChannel> {
    private OnHandlerListener mListener;

    public DanmuChannelInitializer(OnHandlerListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(new IdleStateHandler(45, 45, 45))
                .addLast(new Decoder())
                .addLast(new Encoder())
                .addLast(new NettyClientHandler(mListener));
    }
}
