package com.warm.livelive.data.socket.netty;

/**
 * 作者：warm
 * 时间：2018-02-27 14:10
 * 描述：
 */
public class ByteUtils {

    public static byte[] toDouYuBytes(int num) {
        return reverse(toBytes(num));
    }

    public static int toDouYuInt(byte[] bytes) {
        return toInt(reverse(bytes));
    }

    private static byte[] reverse(byte[] bytes) {
        int rightIndex = bytes.length - 1;
        int i = 0;
        while (i < rightIndex) {
            byte b = bytes[i];
            bytes[i] = bytes[rightIndex];
            bytes[rightIndex] = b;

            i++;
            rightIndex--;
        }
        return bytes;
    }

    public static int toInt(byte[] bytes) {
        int num = 0;
        num |= bytes[0] << 24;
        num |= bytes[1] << 16;
        num |= bytes[2] << 8;
        num |= bytes[3] & 0xff;
        return num;
    }

    public static byte[] toBytes(int num) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (num & 0xFF000000);
        bytes[1] = (byte) ((num & 0xFF0000) >>> 16);
        bytes[2] = (byte) ((num & 0xFF00) >>> 8);
        bytes[3] = (byte) (num & 0xFF);
        return bytes;
    }
}
