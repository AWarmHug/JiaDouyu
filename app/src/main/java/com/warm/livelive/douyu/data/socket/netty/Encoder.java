package com.warm.livelive.douyu.data.socket.netty;

import com.warm.livelive.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 作者：warm
 * 时间：2018-02-27 09:01
 * 描述：
 */
public class Encoder extends MessageToByteEncoder<String> {
    public static final int CLIENT_TO_SERVER = 689;

    /**
     *  public byte[] build() {
     byte[] source = builder.toString().getBytes(StandardCharsets.UTF_8);

     int length = 4 + 4 + 2 + 1 + 1 + source.length + 1;

     byte[] total = new byte[length];

     //头部1
     System.arraycopy(ByteUtils.toBytes(length-4), 0, total, 0, 4);
     //头部2
     System.arraycopy(ByteUtils.toBytes(length-4), 0, total, 4, 4);
     //消息类型
     System.arraycopy(ByteUtils.toBytes(CLIENT_TO_SERVER), 0, total, 8, 2);
     // 0
     System.arraycopy(ByteUtils.toBytes(0), 0, total, 10, 1);
     // 0
     System.arraycopy(ByteUtils.toBytes(0), 0, total, 11, 1);
     //source
     System.arraycopy(source, 0, total, 12, source.length);
     byte tail = '\0';
     System.arraycopy(new byte[]{tail}, 0, total, length - 1, 1);
     return total;
     }
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String sendMsg, ByteBuf byteBuf) throws Exception {
        byte[] source =sendMsg.getBytes(StandardCharsets.UTF_8);

        int length = 4 + 4 + 2 + 1 + 1 + source.length + 1;

        byte[] total = new byte[length];

        //头部1
        System.arraycopy(ByteUtils.toBytes(length-4), 0, total, 0, 4);
        //头部2
        System.arraycopy(ByteUtils.toBytes(length-4), 0, total, 4, 4);
        //消息类型
        System.arraycopy(ByteUtils.toBytes(CLIENT_TO_SERVER), 0, total, 8, 2);
        // 0
        System.arraycopy(ByteUtils.toBytes(0), 0, total, 10, 1);
        // 0
        System.arraycopy(ByteUtils.toBytes(0), 0, total, 11, 1);
        //source
        System.arraycopy(source, 0, total, 12, source.length);
        byte tail = '\0';
        System.arraycopy(new byte[]{tail}, 0, total, length - 1, 1);
        byteBuf.writeBytes(total);
    }
}
