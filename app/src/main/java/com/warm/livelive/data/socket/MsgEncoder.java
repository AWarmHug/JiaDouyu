package com.warm.livelive.data.socket;

import android.support.annotation.NonNull;

/**
 * 作者：warm
 * 时间：2017-12-27 10:43
 * 描述：
 */

public class MsgEncoder {


    private StringBuilder builder;

    public MsgEncoder() {
        builder = new StringBuilder();
    }

    public MsgEncoder addItem(@NonNull String key, @NonNull Object value) {
        if (key.contains("/")) {
            key.replaceAll("/", "@S");
        }

        if (key.contains("@")) {
            key.replaceAll("@", "@A");
        }
        if (value instanceof String) {
            String valueStr = (String) value;
            if (valueStr.contains("/")) {
                valueStr.replaceAll("/", "@S");
            }

            if (valueStr.contains("@")) {
                valueStr.replaceAll("@", "@A");
            }
            value = valueStr;
        }

        builder.append(key)
                .append("@=")
                .append(value)
                .append("/");
        return this;
    }

    public String build() {
        builder.append('\0');
        return builder.toString();
    }


}
