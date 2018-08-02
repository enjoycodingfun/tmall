package com.qf.manager.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TbOrder {
    private String oid;

    private String allprice;

    private String gid;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-dd")
    private Date ordertime;

    private String uid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public String getAllprice() {
        return allprice;
    }

    public void setAllprice(String allprice) {
        this.allprice = allprice == null ? null : allprice.trim();
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }
}