package com.xingshijie.common.network.model;

/**
 * Created by Word Xing  on 2016/5/18.
 */
public class Result<T> {

    public static final int RESULT_SUCCESS = 1;

    private int ret;
    private String msg;
    private String errorCode;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }
}
