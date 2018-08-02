package com.qf.manager.pojo.po;

public class TbOrderitems {
    private String otid;

    private String pid;

    private String oid;

    private Long num;

    private Double price;

    private String pname;

    private String pimage;

    public String getOtid() {
        return otid;
    }

    public void setOtid(String otid) {
        this.otid = otid == null ? null : otid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage == null ? null : pimage.trim();
    }
}