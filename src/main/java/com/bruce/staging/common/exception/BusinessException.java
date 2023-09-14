package com.bruce.staging.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    private String code;

    @Setter
    @Getter
    private String msg;


    public BusinessException(Exception exception) {
        super(exception);
    }

    public BusinessException(String code) {
        this.code = code;
    }

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}