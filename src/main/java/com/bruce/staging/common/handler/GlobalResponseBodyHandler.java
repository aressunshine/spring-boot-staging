package com.bruce.staging.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应结果处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

    /**
     * 所有方法都处理
     *
     * @param returnType    returnType
     * @param converterType converterType
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 返回结果前处理
     *
     * @param body                  body
     * @param returnType            returnType
     * @param selectedContentType   ContentType
     * @param selectedConverterType ConverterType
     * @param request               request
     * @param response              response
     * @return obj
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        return body;
    }
}
