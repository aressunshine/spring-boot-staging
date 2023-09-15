package com.bruce.staging.common.handler;

import com.bruce.staging.common.model.ResponseResult;
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
     * @param responseBody          responseBody
     * @param returnType            returnType
     * @param selectedContentType   ContentType
     * @param selectedConverterType ConverterType
     * @param request               request
     * @param response              response
     * @return obj
     */
    @Override
    public Object beforeBodyWrite(Object responseBody, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        String requestPath = request.getURI().getPath();
        if (requestPath.contains("/v3/api-docs") || requestPath.contains("/swagger-ui.html") || requestPath.contains("/doc.html")) {
            log.info("当前请求路径为接口文档路径，不做响应结果处理");
            return responseBody;
        }
        if (responseBody instanceof ResponseResult) {
            log.info("responseBody is ResponseResult");
            return responseBody;
        }
        log.info("responseBody is {}", responseBody.getClass().getName());
        return ResponseResult.ok(responseBody);
    }
}
