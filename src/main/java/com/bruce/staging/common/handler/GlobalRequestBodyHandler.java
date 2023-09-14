package com.bruce.staging.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 全局请求体处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalRequestBodyHandler extends RequestBodyAdviceAdapter {
    /**
     * 此处如果返回false , 则不执行当前Advice的业务
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return false;
    }

    /**
     * 读取参数前执行, 在此做些编码 / 解密 / 封装参数为对象的操作
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        //获取http请求中原始的body
        String body = IOUtils.toString(httpInputMessage.getBody(), String.valueOf(StandardCharsets.UTF_8));
        return new HttpInputMessage() {
            @Override
            public InputStream getBody() {
                String bodyStr = body;
                return new ByteArrayInputStream(bodyStr.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                return httpInputMessage.getHeaders();
            }
        };
    }
}
