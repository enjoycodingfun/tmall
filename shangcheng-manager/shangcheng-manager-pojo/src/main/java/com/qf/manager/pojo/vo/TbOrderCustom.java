package com.qf.manager.pojo.vo;

import com.qf.manager.pojo.po.TbOrder;

public class TbOrderCustom extends TbOrder{

    private String gname;
    private String address;
    private String uname;
    private String tell;

    @Override
    public String toString() {
        return "TbOrderCustom{" +
                "gname='" + gname + '\'' +
                ", address='" + address + '\'' +
                ", uname='" + uname + '\'' +
                ", tell='" + tell + '\'' +
                '}';
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
