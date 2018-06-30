package com.qf.shop.common.dto;

/**
 * Created by 孙立业 on 2018/5/18.
 */
public class MessageObject {
    //判断是否成功 true false
    private Boolean success;
    //传递的消息
    private String msg;
    //传递对象
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
