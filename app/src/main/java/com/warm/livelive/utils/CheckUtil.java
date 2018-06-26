package com.warm.livelive.utils;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * 作者：warm
 * 时间：2018-02-06 10:14
 * 描述：
 */
public class CheckUtil {

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }


    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    public static boolean isNull(Object reference) {

        return reference == null;
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }

    public static boolean listIsEmpty(List list) {
        return list != null && list.size() != 0;
    }

}
