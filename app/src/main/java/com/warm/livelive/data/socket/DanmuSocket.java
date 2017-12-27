package com.warm.livelive.data.socket;

import android.util.Log;

import com.warm.livelive.config.ApiConfig;
import com.warm.livelive.error.CustomException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 作者：warm
 * 时间：2017-12-27 09:54
 * 描述：
 */

public class DanmuSocket {
    private static final String TAG = "DanmuSocket";

    private WorkExecutor workExecutor;
    private Douyu mDouyu;
    private Socket mSocket;
    private String host = ApiConfig.DANMU_HOST;
    private int port = ApiConfig.DANMU_PORT;

    private BufferedInputStream mBufferIn;
    private BufferedOutputStream mBufferOut;

    public static DanmuSocket INSTANCE = new DanmuSocket();

    public static DanmuSocket getInstance() {
        return INSTANCE;
    }

    private DanmuSocket() {
        mDouyu=Douyu.getInstance();
        workExecutor = WorkExecutor.getInstance();
    }


    private void connect() {
        try {
            mSocket = new Socket(InetAddress.getByName(host).getHostAddress(), port);
            //获取输出流（登入，登出）
            mBufferOut = new BufferedOutputStream(mSocket.getOutputStream());
            //获取输入流（用于获取弹幕）
            mBufferIn = new BufferedInputStream(mSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "connect: ");
        }
    }

    public void prepare(final String roomId, String groupId){
        workExecutor.runWorker(new Runnable() {
            @Override
            public void run() {
                connect();
                loadRoom(roomId);

            }
        });


    }


    public void loadRoom(String roomId) {

        try {
            mBufferOut.write(mDouyu.loadRoom(roomId));
            mBufferOut.flush();
            byte[] bytes = new byte[4096];
            mBufferIn.read(bytes, 0, bytes.length);
           boolean bl= parseLoginRespond(bytes);
            Log.d(TAG, "loadRoom: "+bl);

        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "loadRoom: ");
        }
    }


    public static boolean parseLoginRespond(byte[] respond) {
        boolean rtn = false;

        //返回数据不正确（仅包含12位信息头，没有信息内容）
        if (respond.length <= 12) {
            return rtn;
        }

        //解析返回信息包中的信息内容
        String dataStr = new String(respond, 12, respond.length - 12);
        Log.d(TAG, "parseLoginRespond: "+dataStr);
        //针对登录返回信息进行判断
//        if(StringUtils.contains(dataStr, "type@=loginres")){
//            rtn = true;
//        }
        if (dataStr.contains("type@=loginres")) {
            rtn = true;
        }

        //返回登录是否成功判断结果
        return rtn;
    }


    interface DanmuListener{
        void error(CustomException e);
        void loadSuccess();
        void onDanmu(String dan);
    }



}
