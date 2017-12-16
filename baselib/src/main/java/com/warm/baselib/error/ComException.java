package com.warm.baselib.error;

/**
 * 作者: 51hs_android
 * 时间: 2017/7/27
 * 简介:
 */

public class ComException extends Exception {


    public static final int UNIMPORTANT = Integer.MIN_VALUE;

    public static final int HTTP_ERROR = -1000;

    public static final int UNKONW = -1001;

    public static final int NO_PERMISSIONS = -1002;

    /**
     * 请求成功但是数据为空
     */
    public static final int NO_DATA = -1003;

    /**
     * 登录超时
     */
    public static final int OVER_RUN = -1004;


    /**
     * 发生错误时操作的名字
     */
    private String action;

    private int code;

    /**
     * 发生错误时的操作
     */
    private OnErrorListener mOnErrorListener;


    public interface OnErrorListener {
        void errorAction();
    }


    public OnErrorListener getListener() {
        return mOnErrorListener;
    }


}
