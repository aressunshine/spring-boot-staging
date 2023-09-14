package com.bruce.staging.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应结果类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {

    private String code;

    private String msg;

    private T obj;

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>("200", "", null);
    }

    public static <T> ResponseResult<T> success(String msg) {
        return new ResponseResult<>("200", msg, null);
    }

    public static <T> ResponseResult<T> success(String msg, T t) {
        return new ResponseResult<>("200", msg, t);
    }

    public static <T> ResponseResult<T> error() {
        return new ResponseResult<>("400", "", null);
    }

    public static <T> ResponseResult<T> error(String msg) {
        return new ResponseResult<>("400", msg, null);
    }

    public static <T> ResponseResult<T> error(String success, String msg) {
        return new ResponseResult<>(success, msg, null);
    }
}
