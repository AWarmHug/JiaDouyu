package com.warm.livelive.douyu.data.socket.nativesocek;

import android.util.Log;

import com.warm.livelive.douyu.config.DouyuConfig;
import com.warm.livelive.error.KnownException;
import com.warm.livelive.utils.ByteUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * 作者：warm
 * 时间：2017-12-27 09:54
 * 描述：
 * 使用Netty实现Socket部分
 */

public class DanmuSocket {
    private static final String TAG = "####";
    private static final int DEFAULT_LEN = 8196;

    private WorkExecutor workExecutor;
    private Douyu mDouyu;
    private Socket mSocket;
    private String host = DouyuConfig.DANMU_HOST;
    private int port = DouyuConfig.DANMU_PORT;

    private BufferedInputStream mBufferIn;
    private BufferedOutputStream mBufferOut;
    private OnDanmuListener mOnDanmuListener;

    /**
     * 标识符，通过他来关闭线程
     */
    private boolean mAction;


    public boolean isPrepare() {
        return mAction;
    }

    public void setPrepare(boolean prepare) {
        this.mAction = prepare;
    }

    private static DanmuSocket INSTANCE = new DanmuSocket();

    public static DanmuSocket getInstance() {
        return INSTANCE;
    }

    private DanmuSocket() {
        mDouyu = Douyu.getInstance();
        workExecutor = WorkExecutor.getInstance();
    }

    private boolean checkConnect() {
        if (mSocket == null || mSocket.isClosed()) {
            return connect();
        } else {
            return true;
        }
    }

    private boolean connect() {
        try {
            mSocket = new Socket(InetAddress.getByName(host).getHostAddress(), port);
            //获取输出流（登入，登出）
            mBufferOut = new BufferedOutputStream(mSocket.getOutputStream());
            //获取输入流（用于获取弹幕）
            mBufferIn = new BufferedInputStream(mSocket.getInputStream());
            return mSocket.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void loadAndAction(final String roomId, final String groupId, final OnDanmuListener onDanmuListener) {
        this.mOnDanmuListener = onDanmuListener;
        workExecutor.runWorker(new Runnable() {
            @Override
            public void run() {
                if (checkConnect()) {
                    mAction = true;
                    if (loadRoom(roomId) && loadGroup(roomId, groupId)) {
                        postUiLoadSuccess();
                        keepLife();
                        keepGetDanmu();
                    } else {
                        postUiError(new KnownException("登录失败"));
                    }
                } else {
                    //连接失败提示
                    mAction = true;
                    postUiError(new KnownException("连接失败"));
                }
            }
        });
    }

    private void keepLife() {
        workExecutor.runWorkerSchedule(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "keepLife: ");
                try {
                    write(mDouyu.keepLife());
//                    type@=mrkl/
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "loadRoom: ");
                }
            }
        }, 45 * 1000);
    }

    public void loginOut() {
        mAction = false;
        workExecutor.runWorker(new Runnable() {
            @Override
            public void run() {
                try {
                    write(mDouyu.loginOut());
                    byte[] bytes = read(new byte[DEFAULT_LEN]);
                    MsgDecoder.decode(bytes);
                    postUiLoadOut();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "loadRoom: ");
                }
            }
        });
    }

    private void write(byte[] b) throws IOException {
        if (mAction && mSocket != null && mSocket.isConnected()) {
            Log.d(TAG, "write: " + new String(b));
            mBufferOut.write(b);
            mBufferOut.flush();
        }
    }


    private byte[] read(byte[] bytes) throws IOException {
        if (mAction && mSocket != null && mSocket.isConnected()) {
            byte[] bytes1 = new byte[0];
            int totalLen = 0;
            byte[] clone = new byte[0];
            int len = mBufferIn.read(bytes);
            if (len > 12) {
                do {
                    if (totalLen != 0) {
                        clone = bytes1.clone();
                    }
                    byte[] lens = new byte[4];
                    System.arraycopy(bytes, 0, lens, 0, 4);

                    int rLen = ByteUtil.toIntSmall(lens);
                    Log.d(TAG, "read: " + rLen);

                    Log.d(TAG, "read: len=" + len);
                    byte[] cc = new byte[len];
                    System.arraycopy(bytes, 0, cc, 0, len);

                    totalLen += len;
                    bytes1 = new byte[totalLen];
                    if (clone.length != 0) {
                        System.arraycopy(clone, 0, bytes1, 0, clone.length);
                    }

                    System.arraycopy(cc, 0, bytes1, totalLen - len, len);
                    Log.d(TAG, "read: msg----" + new String(bytes1));
                } while (bytes1[bytes1.length - 1] != '\0');
            }
            Log.d(TAG, "read: " + new String(bytes1));
            return bytes1;
        } else {
            return new byte[0];
        }
    }


    private void postUiLoadOut() {
        mOnDanmuListener.onLoadOut();
        workExecutor.release();

    }

    // type@=error/code@=51
    private void keepGetDanmu() {
        try {
            while (mAction) {
                byte[] data = read(new byte[DEFAULT_LEN]);
                if (new String(data).contains("type")) {
                    List<Map<String, Object>> maps = MsgDecoder.decode(data);
                    if (maps != null) {
                        for (Map<String, Object> map : maps) {
                            Object value = map.get("type");
                            if (value != null && value.equals("chatmsg")) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(map.get("nn"))
                                        .append("说：")
                                        .append(map.get("txt"));
                                postUiDanmu(stringBuilder.toString());
                            }
                        }
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postUiDanmu(final String text) {
        workExecutor.runUi(new Runnable() {
            @Override
            public void run() {
                mOnDanmuListener.onDanmu(text);
            }
        });

    }

    private void postUiLoadSuccess() {
        workExecutor.runUi(new Runnable() {
            @Override
            public void run() {
                mOnDanmuListener.onLoadSuccess();
            }
        });
    }

    private void postUiError(final KnownException e) {
        workExecutor.runUi(new Runnable() {
            @Override
            public void run() {
                mOnDanmuListener.onError(e);
            }
        });
    }


    private boolean loadGroup(String roomId, String groupId) {
        try {
            write(mDouyu.loadGroup(roomId, groupId));
            return true;
        } catch (IOException e) {
            Log.d(TAG, "loadRoom: ");
            return false;
        }
    }


    private boolean loadRoom(String roomId) {
//type@=loginres/userid@=0/roomgroup@=0/pg@=0/sessionid@=0/username@=/nickname@=/live_stat@=0/is_illegal@=0/ill_ct@=/ill_ts@=0/now@=0/ps@=0/es@=0/it@=0/its@=0/npv@=0/best_dlev@=0/cur_lev@=0/nrc@=4284481613/ih@=0/sid@=72963/sahf@=0/
        try {
            write(mDouyu.loadRoom(roomId));
            byte[] bytes = read(new byte[DEFAULT_LEN]);

            List<Map<String, Object>> ss = MsgDecoder.decode(bytes);
            if (ss != null && ss.size() != 0) {
                return ss.get(0).get("type").equals("loginres");
            } else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "loadRoom: ");
            return false;
        }
    }


    public interface OnDanmuListener {
        void onError(KnownException e);

        void onLoadSuccess();

        void onDanmu(String dan);

        void onLoadOut();
    }

    /**
     * 释放所有，并且关闭socket和线程
     */
    public void release() {
        if (mSocket != null && mSocket.isConnected()) {
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
