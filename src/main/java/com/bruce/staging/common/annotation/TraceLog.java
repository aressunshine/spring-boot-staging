package com.bruce.staging.common.annotation;

import java.lang.annotation.*;

/**
 * 日志链路标记
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TraceLog {
}
