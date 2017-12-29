package com.warm.livelive.utils;

/**
 * 作者：warm
 * 时间：2017-12-27 12:08
 * 描述：
 */

public class ByteUtils {


    public static int toInt(byte[] bytes) {
        int num = 0;
        num |= bytes[0] & 0xff;
        num |= bytes[1] << 8;
        num |= bytes[2] << 16;
        num |= bytes[3] << 24;
        return num;
    }

    // int转byte[] 小端，
    public static byte[] toBytes(int num) {
        byte[] b = new byte[4];
        b[0] = (byte) (num & 0xff);
        b[1] = (byte) (num >> 8 & 0xff);
        b[2] = (byte) (num >> 16 & 0xff);
        b[3] = (byte) (num >> 24 & 0xff);
        return b;
    }

}

