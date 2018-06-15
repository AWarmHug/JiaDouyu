package com.warm.livelive.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 作者：warm
 * 时间：2018-06-15 09:38
 * 描述：
 */
public class TimeUtil {

    private static final DateFormat ACTIVITY_FORMAT = new SimpleDateFormat("MM月dd日 HH:mm");

    public static String time2Activity(long time) {
        return ACTIVITY_FORMAT.format(time);
    }

}
