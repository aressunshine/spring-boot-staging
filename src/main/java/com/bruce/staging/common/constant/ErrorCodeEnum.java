package com.bruce.staging.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举类
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    OK("200", "ok"),
    BAD_REQUEST("400", "Bad Request"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    SERVER_ERROR("500", "Internal Server Error"),
    UNAVAILABLE("503", "Service Unavailable");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String message;
}
