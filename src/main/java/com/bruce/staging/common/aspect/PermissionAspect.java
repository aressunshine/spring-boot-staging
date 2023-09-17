package com.bruce.staging.common.aspect;

import com.bruce.staging.common.annotation.Permission;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(2)
@Aspect
@Component
public class PermissionAspect {

    @Around("@annotation(permission)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, Permission permission) {
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
