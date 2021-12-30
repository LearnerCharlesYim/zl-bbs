package com.charles.zlbbs.domain.dto;

import com.charles.zlbbs.domain.enu.ResultCode;
import com.charles.zlbbs.exception.BaseErrorInfoInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class R {
    private Integer code;
    private String message;
    private Object data;

    public R() {
    }

    public R(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static R ok() {
        R r = new R();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        r.setData(data);
        return r;
    }

    public static R fail(BaseErrorInfoInterface errorInfo) {
        R r = new R();
        r.setCode(errorInfo.getCode());
        r.setMessage(errorInfo.getMessage());
        return r;
    }

    public static R fail(Integer code,String message) {
        R r = new R();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static R fail(String message) {
        R r = new R();
        r.setCode(ResultCode.COMMON_FAIL.getCode());
        r.setMessage(message);
        return r;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
