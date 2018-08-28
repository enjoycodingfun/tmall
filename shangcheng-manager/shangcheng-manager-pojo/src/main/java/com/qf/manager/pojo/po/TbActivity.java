package com.qf.manager.pojo.po;

import org.springframework.stereotype.Component;

@Component
public class TbActivity {
    private String aid;

    private String aname;

    private Integer full;

    private Integer minus;

    private Integer discount;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public Integer getFull() {
        return full;
    }

    public void setFull(Integer full) {
        this.full = full;
    }

    public Integer getMinus() {
        return minus;
    }

    public void setMinus(Integer minus) {
        this.minus = minus;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}