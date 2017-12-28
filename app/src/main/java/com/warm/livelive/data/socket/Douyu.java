package com.warm.livelive.data.socket;

import com.warm.livelive.data.bean.SendBean;
import com.warm.livelive.utils.FormatTransfer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：warm
 * 时间：2017-12-27 11:10
 * 描述：
 */

public class Douyu {
    public static final int CLIENT_TO_SERVER = 689;


    private static final Douyu ourInstance = new Douyu();


    public static Douyu getInstance() {
        return ourInstance;
    }

    private Douyu() {
    }


    public byte[] loadRoom(String roomId) {
        return toByte(new MsgEncoder()
                .addItem("type", "loginreq")
                .addItem("roomid", roomId)
                .build());
    }

    public byte[] loadGroup(String roomId, String groupId) {

        return toByte(new MsgEncoder()
                .addItem("type", "joingroup")
                .addItem("rid", roomId)
                .addItem("gid", groupId)
                .build());
    }

    public byte[] keepLife() {
        List<SendBean> sendBeens=new ArrayList<>();
        sendBeens.add(new SendBean("type","mrkl"));
        return sent(sendBeens);
    }

    public byte[] loginOut(){
        return toByte(new MsgEncoder()
                .addItem("type", "logout")
                .build());
    }

    public byte[] sent(List<SendBean> sendBeen){
        MsgEncoder msgEncoder=new MsgEncoder();
        for (int i = 0; i < sendBeen.size(); i++) {
            msgEncoder.addItem(sendBeen.get(i).key,sendBeen.get(i).value)   ;
        }
        return toByte(msgEncoder.build());
    }


    private byte[] toByte(String data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.write(FormatTransfer.toLH(data.length() + 8), 0, 4);
            dataOutputStream.write(FormatTransfer.toLH(data.length() + 8), 0, 4);
            dataOutputStream.write(FormatTransfer.toLH(CLIENT_TO_SERVER), 0, 2);
            dataOutputStream.write(0);
            dataOutputStream.write(0);
            dataOutputStream.writeBytes(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }


}
