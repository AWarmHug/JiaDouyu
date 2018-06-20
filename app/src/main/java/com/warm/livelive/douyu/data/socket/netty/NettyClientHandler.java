package com.warm.livelive.douyu.data.socket.netty;

import android.util.Log;

import com.warm.livelive.error.KnownException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 作者：warm
 * 时间：2018-02-27 08:52
 * 描述：
 */
class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private OnLoadListener mListener;

    public NettyClientHandler(OnLoadListener loadListener) {
        this.mListener = loadListener;
    }

    /**
     * 设置心跳
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            long now = System.currentTimeMillis();
//            Log.d("Netty", "userEventTriggered: "+now);
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    ctx.writeAndFlush(Douyu.getInstance().keepLife());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 出错后的回调
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
//        Log.d("Netty", "exceptionCaught: ");
        mListener.onError(new KnownException("弹幕获取出错"));
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        Log.d("netty", "channelRead: " + msg);
        String ss = (String) msg;
       if (ss.contains("chatmsg")) {
           Message message = new Message(ss);
           if (message.getMap().get("type") != null && message.getMap().get("txt") != null) {
               mListener.onMessage(message.getMap().get("type"), message.getMap().get("txt"));
           }
       }
    }
}
