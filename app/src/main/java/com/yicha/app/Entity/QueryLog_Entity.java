package com.yicha.app.Entity;

import java.util.List;

public class QueryLog_Entity {
    private String addtime;
    private Object brand;
    private String browser;
    private Object buytime;
    private Object casenumber;
    private String city;
    private String frompage;
    private String icon;
    private int id;
    private String ip;
    private String name;
    private int newlevel;
    private String param;
    private List<ParamListBean> paramList;
    private String price;
    private String type;
    private int uid;
    private Object where;

    public String getAddtime() {
        return this.addtime;
    }

    public Object getBrand() {
        return this.brand;
    }

    public String getBrowser() {
        return this.browser;
    }

    public Object getBuytime() {
        return this.buytime;
    }

    public Object getCasenumber() {
        return this.casenumber;
    }

    public String getCity() {
        return this.city;
    }

    public String getFrompage() {
        return this.frompage;
    }

    public String getIcon() {
        return this.icon;
    }

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }

    public String getName() {
        return this.name;
    }

    public int getNewlevel() {
        return this.newlevel;
    }

    public String getParam() {
        return this.param;
    }

    public List<ParamListBean> getParamList() {
        return this.paramList;
    }

    public String getPrice() {
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    public int getUid() {
        return this.uid;
    }

    public Object getWhere() {
        return this.where;
    }

    public void setAddtime(String paramString) {
        this.addtime = paramString;
    }

    public void setBrand(Object paramObject) {
        this.brand = paramObject;
    }

    public void setBrowser(String paramString) {
        this.browser = paramString;
    }

    public void setBuytime(Object paramObject) {
        this.buytime = paramObject;
    }

    public void setCasenumber(Object paramObject) {
        this.casenumber = paramObject;
    }

    public void setCity(String paramString) {
        this.city = paramString;
    }

    public void setFrompage(String paramString) {
        this.frompage = paramString;
    }

    public void setIcon(String paramString) {
        this.icon = paramString;
    }

    public void setId(int paramInt) {
        this.id = paramInt;
    }

    public void setIp(String paramString) {
        this.ip = paramString;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public void setNewlevel(int paramInt) {
        this.newlevel = paramInt;
    }

    public void setParam(String paramString) {
        this.param = paramString;
    }

    public void setParamList(List<ParamListBean> paramList1) {
        this.paramList = paramList1;
    }

    public void setPrice(String paramString) {
        this.price = paramString;
    }

    public void setType(String paramString) {
        this.type = paramString;
    }

    public void setUid(int paramInt) {
        this.uid = paramInt;
    }

    public void setWhere(Object paramObject) {
        this.where = paramObject;
    }

    public static class ParamListBean {
        private String name;
        private String param;
        private String value;

        public String getName() {
            return this.name;
        }

        public String getParam() {
            return this.param;
        }

        public String getValue() {
            return this.value;
        }

        public void setName(String paramString) {
            this.name = paramString;
        }

        public void setParam(String paramString) {
            this.param = paramString;
        }

        public void setValue(String paramString) {
            this.value = paramString;
        }
    }
}
