package com.bruce.staging.common.model;

import com.bruce.staging.common.constant.ErrorCodeEnum;
import com.bruce.staging.common.constant.StringConst;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.MDC;

/**
 * 通用响应结果类
 */
@Schema(name = "响应结果", description = "响应结果实体类")
public class ResponseResult<T> {

    @Schema(title = "响应码，成功：200，失败：400", type = "string", example = "200")
    private String code;

    @Schema(title = "错误信息", type = "string", example = "Bad Request")
    private String msg;

    @Schema(title = "链路id", type = "string", example = "Bad Request")
    private String traceId;

    @Schema(title = "响应实体", type = "object", example = "example")
    private T obj;

    public ResponseResult() {

    }

    public ResponseResult(ErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMessage();
        this.traceId = MDC.get(StringConst.TRACE_ID);
    }

    public ResponseResult(String code, String msg, T obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
        this.traceId = MDC.get(StringConst.TRACE_ID);
    }

    public static <T> ResponseResult<T> ok() {
        return new ResponseResult<>(ErrorCodeEnum.OK);
    }

    public static <T> ResponseResult<T> ok(String msg) {
        return new ResponseResult<>(ErrorCodeEnum.OK.getCode(), msg, null);
    }

    public static <T> ResponseResult<T> ok(T t) {
        return new ResponseResult<>(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.OK.getMessage(), t);
    }

    public static <T> ResponseResult<T> ok(String msg, T t) {
        return new ResponseResult<>(ErrorCodeEnum.OK.getCode(), msg, t);
    }

    public static <T> ResponseResult<T> error() {
        return new ResponseResult<>(ErrorCodeEnum.BAD_REQUEST);
    }

    public static <T> ResponseResult<T> error(String msg) {
        return new ResponseResult<>(ErrorCodeEnum.BAD_REQUEST.getCode(), msg, null);
    }

    public static <T> ResponseResult<T> error(String code, String msg) {
        return new ResponseResult<>(code, msg, null);
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

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
