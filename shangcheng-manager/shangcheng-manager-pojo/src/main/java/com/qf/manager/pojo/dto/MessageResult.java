package com.qf.manager.pojo.dto;

/**
 * 封装从Action到页面的传输对象
 * User: DHC
 * Date: 2018/8/2
 * Time: 11:57
 * Version:V1.0
 */
public class MessageResult {
    private Boolean success;
    private String msg;
    private Object data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
