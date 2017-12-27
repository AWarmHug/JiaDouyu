package com.warm.livelive.data.socket;

import com.warm.livelive.utils.FormatTransfer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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


    private byte[] toByte(String data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.write(FormatTransfer.toLH(data.length()+8), 0, 4);
            dataOutputStream.write(FormatTransfer.toLH(data.length()+8), 0, 4);
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
