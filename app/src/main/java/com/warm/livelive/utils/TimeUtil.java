package com.warm.livelive.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 作者：warm
 * 时间：2018-06-15 09:38
 * 描述：
 */
public class TimeUtil {

    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;

    private static final DateFormat ACTIVITY_FORMAT = new SimpleDateFormat("MM月dd日 HH:mm");

    public static String time2Activity(long time) {
        return ACTIVITY_FORMAT.format(time);
    }

    public static String time2Duration(long time) {

        long hours = time / 3600;
        time = time % 3600;
        long minutes = time / 60;
        time = time % 60;

        if (hours > 0) {


            return String.format(Locale.CHINA, "%d:%d:%d", hours, minutes, time);
        } else {
            return String.format(Locale.CHINA, "%d:%d", minutes, time);
        }

    }

}
