package com.warm.livelive.data.socket.netty;

import android.util.Log;

import com.warm.livelive.config.ApiConfig;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * 作者：warm
 * 时间：2018-02-26 16:51
 * 描述：
 */
public class NettyClient {
    private static final NettyClient ourInstance = new NettyClient();

    public static NettyClient getInstance() {
        return ourInstance;
    }

    private Bootstrap mBootstrap;
    private NioEventLoopGroup group;
    private SocketChannel socketChannel;
    private OnHandlerListener mListener;


    private NettyClient() {
        mBootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        mBootstrap.channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .group(group)
                .remoteAddress(new InetSocketAddress(ApiConfig.DANMU_HOST, ApiConfig.DANMU_PORT));
    }

    public void startConnect(OnHandlerListener listener) {
        this.mListener = listener;

        if (socketChannel==null||!socketChannel.isOpen()){
            Log.d("Netty", "连接开始...");

            if (!connect()) {
                Log.d("Netty", "连接失败");
            }else {
                socketChannel.writeAndFlush(Douyu.getInstance().keepLife());
                Log.d("Netty", "连接成功");

            }
        }

    }

    private boolean connect() {
        boolean success;
        try {
            ChannelFuture future = mBootstrap.handler(new DanmuChannelInitializer(mListener))
                    .connect(new InetSocketAddress(ApiConfig.DANMU_HOST, ApiConfig.DANMU_PORT))
                    .sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                Log.d("Netty", "connect: 可以连接");
            }
        } catch (InterruptedException e) {
            return false;
        }


        success = socketChannel.isOpen();

        return success;

    }

    public void send(String msg) {
//        if (socketChannel != null && socketChannel.isOpen()) {
        ChannelFuture channelFuture = null;
        channelFuture = socketChannel.writeAndFlush(msg);
        channelFuture.addListener(new GenericFutureListener<ChannelFuture>() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (!channelFuture.isSuccess()) {
                    channelFuture.channel().close();
                }
            }
        });
        //        }
    }
}
