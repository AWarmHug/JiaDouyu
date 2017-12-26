package com.warm.livelive.error;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * 作者: 51hs_android
 * 时间: 2017/7/27
 * 简介:
 */

public class CustomException extends Exception {




    public static final int UNIMPORTANT = Integer.MIN_VALUE;

    public static final int HTTP_ERROR = -1000;

    public static final int UNKONW = -1001;

    public static final int NO_PERMISSIONS = -1002;

    public static final int UN_LOGIN = -1004;

    /**
     * 请求成功但是数据为空
     */
    public static final int NO_DATA = -1003;

    /**
     * 登录超时
     */
    public static final int OVER_RUN = -1004;




    /**
     * 发生错误后显示的图片和信息
     */
    private ErrorShowInfo errorShowInfo;


    public ErrorShowInfo getErrorShowInfo() {
        return errorShowInfo;
    }

    public void setErrorShowInfo(ErrorShowInfo errorShowInfo) {
        this.errorShowInfo = errorShowInfo;
    }

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    /**
     * 发生错误时操作的名字
     */
    private String actionName;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * 发生错误时的操作
     */
    private OnErrorListener mOnErrorListener;

    public OnErrorListener getListener() {
        return mOnErrorListener;
    }

    public void setOnErrorListener(OnErrorListener errorListener) {
        this.mOnErrorListener = errorListener;
    }


    public CustomException(int errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException(int error, String message) {
        this(error, message, null);
    }


    public CustomException(String message) {
        this(UNIMPORTANT, message, null);
    }

    public CustomException(String message, OnErrorListener listener) {
        this(UNIMPORTANT, message, listener);
    }

    public CustomException(String message, String actionName, OnErrorListener listener) {
        this(UNIMPORTANT, message, actionName, listener);
    }


    public CustomException(int errorCode, String message, OnErrorListener listener) {
        this(errorCode, message, null, listener);
    }

    public CustomException(int errorCode, String message, String actionName, OnErrorListener mOnErrorListener) {
        this(errorCode,message,null,actionName, mOnErrorListener);

    }
    public CustomException(int errorCode, String message, ErrorShowInfo errorShowInfo, String actionName, OnErrorListener mOnErrorListener) {
        super(message);
        this.errorCode = errorCode;
        this.errorShowInfo=errorShowInfo;
        this.actionName = actionName;
        this.mOnErrorListener = mOnErrorListener;
    }



    public interface OnErrorListener {
        void errorAction();
    }

    public static class ErrorShowInfo{
        private  @NonNull String message;
        private @DrawableRes int res;

        public ErrorShowInfo(@DrawableRes int res,@NonNull String message) {
            this.message = message;
            this.res = res;
        }

        @NonNull
        public String getMessage() {
            return message;
        }

        public void setMessage(@NonNull String message) {
            this.message = message;
        }

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "errorMessage=" + getMessage() +
                ", errorCode=" + errorCode +
                '}';
    }
}
