package com.yicha.app.Entity;

/**
 * Created by 谢军 on 2018/8/6 0006.
 */

public class BaseEntity<T>{
    private int status;
    private String msg;
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
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}