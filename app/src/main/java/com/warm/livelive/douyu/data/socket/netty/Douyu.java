package com.warm.livelive.douyu.data.socket.netty;

import com.warm.livelive.douyu.data.bean.douyu.SendBean;
import com.warm.livelive.douyu.data.socket.nativesocek.MsgEncoder;

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


    public String loadRoom(String roomId) {

        return new MsgEncoder()
                .addItem("type", "loginreq")
                .addItem("roomid", roomId).toString();
    }

    public String loadGroup(String roomId, String groupId) {

        return new MsgEncoder()
                .addItem("type", "joingroup")
                .addItem("rid", roomId)
                .addItem("gid", groupId).toString();
    }

    public String keepLife() {
        return sent(new SendBean("type", "mrkl"));
    }

    public String loginOut() {
        return new MsgEncoder()
                .addItem("type", "logout").toString();
    }

    public String sent(SendBean... sendBeen) {
        MsgEncoder msgEncoder = new MsgEncoder();
        for (int i = 0; i < sendBeen.length; i++) {
            msgEncoder.addItem(sendBeen[i].key, sendBeen[i].value);
        }
        return msgEncoder.toString();
    }


}
