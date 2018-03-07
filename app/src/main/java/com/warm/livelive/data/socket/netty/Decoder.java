package com.warm.livelive.data.socket.netty;

import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 作者：warm
 * 时间：2018-02-27 08:52
 * 描述：
 */
public class Decoder extends ByteToMessageDecoder {
    public static final byte[] SERVER_FLAG = new byte[]{(byte) 0xb2, 0x02, 0x00, 0x00};
    private int mMessageSize = -1;
    private byte[] size = new byte[4];
    private byte[] size2 = new byte[4];
    private byte[] flag = new byte[4];
    private byte[] mByteArrayCache = new byte[4096];

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 一条斗鱼 Socket 消息包含 5 个部分：
        // 1. 数据长度，大小为后四部分的字节长度，占 4 个字节。
        // 2. 内容和第一部分一样，占 4 个字节。
        // 3. 斗鱼固定的请求码，占 4 个字节。
        //     本地 -> 服务器是 0xb1,0x02,0x00,0x00 。
        //     服务器 -> 本地是 0xb2,0x02,0x00,0x00 。
        // 4. 消息内容。
        // 5. 尾部一个空字节 0x00 ，占 1 个字节。
        if (mMessageSize == -1) {
            if (in.readableBytes() < 12) {
                return;
            }
            in.getBytes(in.readerIndex(), size);
            in.getBytes(in.readerIndex() + 4, size2);
            int messageSize = ByteUtils.toDouYuInt(size);
            int messageSize2 = ByteUtils.toDouYuInt(size2);
            if (messageSize != messageSize2) {
                cleanBuffer(in);
                return;
            }
            in.getBytes(in.readerIndex() + 8, flag);
            if (!Arrays.equals(SERVER_FLAG, flag)) {
                cleanBuffer(in);
                return;
            }
            mMessageSize = messageSize;
        }

        if (mMessageSize != -1) {
            int currentMessageLength = mMessageSize + 4;
            if (in.readableBytes() >= currentMessageLength) {
                if (mByteArrayCache.length < currentMessageLength) {
                    mByteArrayCache = new byte[currentMessageLength];
                }
                in.readBytes(mByteArrayCache, 0, currentMessageLength);
                String message = new String(mByteArrayCache, 12, currentMessageLength - 13, "utf-8");
                //System.out.println(message);
                out.add(message);
                mMessageSize = -1;
            }
        }
    }

    private void cleanBuffer(ByteBuf in) {
        int start = in.bytesBefore((byte) 0);
        if (start < 0) {
            in.readBytes(in.readableBytes());
        } else {
            in.readBytes(start + 1);
        }
    }
}
