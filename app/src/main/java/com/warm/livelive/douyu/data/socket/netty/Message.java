package com.warm.livelive.douyu.data.socket.netty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者：warm
 * 时间：2018-03-06 15:44
 * 描述：
 */
public class Message {

    private Map<String, String> map;

    public Message(String msg) {
        map = split(replace(msg));
    }

    private String replace(String msg) {

        while (msg.contains("@S") || msg.contains("@A")) {
            msg = msg.replaceAll("@S", "/").replaceAll("@A", "@");
        }
        return msg;
    }

    private Map<String, String> split(String msg) {
        String[] ss = msg.split("/");
        Map<String, String> map = new LinkedHashMap<>();
        for (String s : ss) {
            String[] childs = s.split("@=");
            if (childs.length == 2) {
                map.put(childs[0], childs[1]);
            }
        }
        return map;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
