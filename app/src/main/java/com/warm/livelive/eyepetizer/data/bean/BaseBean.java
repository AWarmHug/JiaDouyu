package com.warm.livelive.eyepetizer.data.bean;

/**
 * 作者：warm
 * 时间：2018-06-17 15:07
 * 描述：
 */
public class BaseBean<T> {
    public static final int ERROR_404=404;


    /**
     * errorCode : 404
     * errorMessage : Resource Not Found
     */

    private int errorCode;
    private String errorMessage;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
