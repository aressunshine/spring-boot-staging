package com.bruce.staging.common.filter;

import com.bruce.staging.common.wrapper.RepeatableRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Objects;

/**
 * Repeatable 过滤器
 */
@Slf4j
public class RepeatableFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            String contentType = request.getContentType();
            if (StringUtils.isNotBlank(contentType) && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                requestWrapper = new RepeatableRequestWrapper((HttpServletRequest) request, response);
            }
        }
        chain.doFilter(Objects.isNull(requestWrapper) ? request : requestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}
