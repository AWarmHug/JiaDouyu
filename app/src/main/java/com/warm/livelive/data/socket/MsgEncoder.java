package com.warm.livelive.data.socket;

import android.support.annotation.NonNull;

import com.warm.livelive.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

/**
 * 作者：warm
 * 时间：2017-12-27 10:43
 * 描述：
 * byte[] source = builder.toString().getBytes(StandardCharsets.UTF_8);
 * <p>
 * int length =  4 + 2 + 1 + 1 + source.length + 1;
 * <p>
 * byte[] total = new byte[length];
 * <p>
 * //头部1
 * System.arraycopy(ByteUtils.toBytes(length), 0, total, 0, 4);
 * //头部2
 * System.arraycopy(ByteUtils.toBytes(length), 0, total, 4, 4);
 * //消息类型
 * System.arraycopy(ByteUtils.toBytes(CLIENT_TO_SERVER), 0, total, 8, 2);
 * // 0
 * System.arraycopy(ByteUtils.toBytes(0), 0, total, 10, 1);
 * // 0
 * System.arraycopy(ByteUtils.toBytes(0), 0, total, 11, 1);
 * //source
 * System.arraycopy(source, 0, total, 12, source.length);
 * byte tail = '\0';
 * System.arraycopy(new byte[]{tail}, 0, total, length - 1, 1);
 * return total;
 */

public class MsgEncoder {
    public static final int CLIENT_TO_SERVER = 689;


    private StringBuilder builder;

    public MsgEncoder() {
        builder = new StringBuilder();
    }

    public MsgEncoder addItem(@NonNull String key, @NonNull Object value) {
        if (key.contains("/")) {
            key = key.replaceAll("/", "@S");
        }

        if (key.contains("@")) {
            key = key.replaceAll("@", "@A");
        }
        if (value instanceof String) {
            String valueStr = (String) value;
            if (valueStr.contains("/")) {
                value = valueStr.replaceAll("/", "@S");
            }

            if (valueStr.contains("@")) {
                value = valueStr.replaceAll("@", "@A");
            }
        }

        builder.append(key)
                .append("@=")
                .append(value)
                .append("/");
        return this;
    }
    @Override
    public String toString() {
        return builder.toString();
    }

    public byte[] build() {
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


}
