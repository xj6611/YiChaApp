package com.yicha.app.Entity;

import java.io.Serializable;

/**
 * 登录信息bean
 * Created by 谢军 on 2018/8/6 0006.
 */

public class UserEntity implements Serializable {
    /**
     * id : 1
     * username : admin
     * pwd : e10adc3949ba59abbe56e057f20f883e
     * name : 管理员
     * sex : 男
     * number : null
     * company : null
     * phone : null
     * header : null
     * token : bea440a877e331ac9e2fce6600076683f3e83ea9
     * logins : 115
     * last_login_time : 1543121365
     * addtime : 1542318309
     */

    private int id;
    private String username;
    private String pwd;
    private String name;
    private String sex;
    private String number;
    private String company;
    private Object phone;
    private String header;
    private String token;
    private int logins;
    private int last_login_time;
    private int addtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLogins() {
        return logins;
    }

    public void setLogins(int logins) {
        this.logins = logins;
    }

    public int getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(int last_login_time) {
        this.last_login_time = last_login_time;
    }

    public int getAddtime() {
        return addtime;
    }

    public void setAddtime(int addtime) {
        this.addtime = addtime;
    }
}

