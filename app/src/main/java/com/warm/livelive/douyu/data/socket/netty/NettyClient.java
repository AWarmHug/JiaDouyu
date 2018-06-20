package com.warm.livelive.douyu.data.socket.netty;

import android.support.annotation.NonNull;
import android.util.Log;

import com.warm.livelive.error.KnownException;
import com.warm.livelive.utils.CheckUtil;

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


    private NettyClient() {
        mBootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        mBootstrap.channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .group(group);
    }

    public void startConnect(InetSocketAddress inetSocketAddress, @NonNull OnLoadListener loadListener) {
        CheckUtil.checkNotNull(inetSocketAddress);
        CheckUtil.checkNotNull(loadListener);
        OnLoadListener my=new OnLoadListener() {
            @Override
            public void onLoading() {
                loadListener.onLoading();
            }

            @Override
            public void onError(KnownException e) {
                loadListener.onError(e);

            }

            @Override
            public void onSuccess() {
                loadListener.onSuccess();
            }

            @Override
            public void onMessage(String type, String msg) {
                if (type.equals("chatmsg")){
                    loadListener.onMessage(type, msg);
                }else {
                    endConnect();
                }
            }
        };
        connect(inetSocketAddress, my);
    }

    private void connect(InetSocketAddress inetSocketAddress, OnLoadListener listener) {
        boolean success;
        try {
            ChannelFuture future = mBootstrap
                    .handler(new DanmuChannelInitializer(listener))
                    .connect(inetSocketAddress)
                    .sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                success = socketChannel.isOpen();
            } else {
                success = false;
            }
        } catch (InterruptedException e) {
            success = false;
        }
        if (success) {
            Log.d("netty", "连接成功");
            send(Douyu.getInstance().keepLife());
            listener.onSuccess();
        } else {
            Log.d("netty", "连接失败");
            listener.onError(new KnownException("连接失败"));
        }
    }

    public void send(String msg) {
        CheckUtil.checkNotNull(socketChannel);
        ChannelFuture channelFuture;
        channelFuture = socketChannel.writeAndFlush(msg);
        channelFuture.addListener(new GenericFutureListener<ChannelFuture>() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) {
                if (!channelFuture.isSuccess()) {
                    channelFuture.channel().close();
                }
            }
        });
    }

    public void endConnect() {
        if (socketChannel != null) {
            socketChannel.close();
        }
    }


}
