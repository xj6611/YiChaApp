package com.yicha.app.Entity;

/**
 * Created by 谢军 on 2018/8/6 0006.
 */

public class Base2Entity<T>{
    private int status;
    private String masge;
    private T info;


    public T getData() {
        return info;
    }

    public void setData(T data) {
        this.info = data;
    }

    public int getResult() {
        return status;
    }

    public void setResult(int result) {
        this.status = result;
    }

    public String getMsg() {
        return masge;
    }

    public void setMsg(String msg) {
        this.masge = msg;
    }


}