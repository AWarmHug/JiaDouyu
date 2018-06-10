package com.warm.livelive.douyu.data.socket.nativesocek;

import com.warm.livelive.douyu.data.bean.SendBean;

/**
 * 作者：warm
 * 时间：2017-12-27 11:10
 * 描述：
 */

public class Douyu {


    private static final Douyu ourInstance = new Douyu();


    public static Douyu getInstance() {
        return ourInstance;
    }

    private Douyu() {
    }


    public byte[] loadRoom(String roomId) {
        return new MsgEncoder()
                .addItem("type", "loginreq")
                .addItem("roomid", roomId)
                .build();
    }

    public byte[] loadGroup(String roomId, String groupId) {

        return new MsgEncoder()
                .addItem("type", "joingroup")
                .addItem("rid", roomId)
                .addItem("gid", groupId)
                .build();
    }

    public byte[] keepLife() {
        return sent(new SendBean("type", "mrkl"));
    }

    public byte[] loginOut() {
        return new MsgEncoder()
                .addItem("type", "logout")
                .build();
    }

    public byte[] sent(SendBean... sendBeen) {
        MsgEncoder msgEncoder = new MsgEncoder();
        for (int i = 0; i < sendBeen.length; i++) {
            msgEncoder.addItem(sendBeen[i].key, sendBeen[i].value);
        }
        return msgEncoder.build();
    }


}
