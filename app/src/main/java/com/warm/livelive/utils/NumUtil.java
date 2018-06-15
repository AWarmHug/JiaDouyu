package com.warm.livelive.utils;

/**
 * 作者：warm
 * 时间：2017-12-26 13:50
 * 描述：
 */

public class NumUtil {

    public static String mini(int num) {
        StringBuilder stringBuffer = new StringBuilder();
        if (num >= 1000) {
            stringBuffer.append(DoubleUtil.round(num*1f / 10000, 1)).append("万");
        } else {
            stringBuffer.append(num);
        }
        return stringBuffer.toString();
    }


}
