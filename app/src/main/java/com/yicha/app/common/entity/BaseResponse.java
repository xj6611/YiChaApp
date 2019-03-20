package com.yicha.app.common.entity;

/**
 * Created by dulei on 2017/3/17.
 */

public class BaseResponse<T> {
    private int status;
    private String message;
    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getResult() {
        return status;
    }

    public void setResult(int result) {
        this.status = result;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }
}

