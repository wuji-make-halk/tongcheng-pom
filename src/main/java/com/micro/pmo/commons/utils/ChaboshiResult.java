package com.micro.pmo.commons.utils;

import java.io.Serializable;

/**
 * @Author WangQiLong
 * @Date 2019/7/24 上午9:56
 * 查博士返回信息
 **/
public class ChaboshiResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Code;

    private String Message;

    private String orderId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
