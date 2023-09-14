package com.bruce.staging.common.config;

import com.bruce.staging.common.interceptor.HttpHandlerInterceptor;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SpringWebMvcConfig implements WebMvcConfigurer {

    @Resource
    private HttpHandlerInterceptor httpHandlerInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径，addResourceLocations是文件存放的真实路径
        //registry.addResourceHandler("/**").addResourceLocations("classpath:/file/");
        registry.addResourceHandler("/file/**").addResourceLocations("file:/Users/bruce/document/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpHandlerInterceptor).addPathPatterns("/**").excludePathPatterns("/tourist/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                .maxAge(1800);
    }
}
