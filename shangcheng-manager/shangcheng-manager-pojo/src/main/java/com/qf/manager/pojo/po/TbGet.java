package com.qf.manager.pojo.po;

public class TbGet {
    private String gid;

    private String address;

    private String gnama;

    private String tell;

    private String uid;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getGnama() {
        return gnama;
    }

    public void setGnama(String gnama) {
        this.gnama = gnama == null ? null : gnama.trim();
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell == null ? null : tell.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    @Override
    public String toString() {
        return "TbGet{" +
                "gid='" + gid + '\'' +
                ", address='" + address + '\'' +
                ", gnama='" + gnama + '\'' +
                ", tell='" + tell + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}