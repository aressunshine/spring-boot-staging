package com.bruce.staging.common.handler;

import com.bruce.staging.common.constant.ErrorCodeEnum;
import com.bruce.staging.common.exception.BusinessException;
import com.bruce.staging.common.model.ResponseResult;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseResult<String> handleException(Exception e) {
        if (e instanceof MethodArgumentNotValidException exception) {
            List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
            String message = allErrors.stream().map(s -> s.getDefaultMessage()).collect(Collectors.joining(";"));
            log.error(e.getMessage(), e);
            return ResponseResult.error(ErrorCodeEnum.BAD_REQUEST.getCode(), message);
        }
        if (e instanceof ConstraintViolationException exception) {
            log.error(e.getMessage(), e);
            return ResponseResult.error(ErrorCodeEnum.BAD_REQUEST.getCode(), exception.getMessage());
        }
        if (e instanceof BusinessException exception) {
            String code = exception.getCode();
            log.error(exception.getMessage(), exception);
            return ResponseResult.error(code, exception.getMessage());
        }
        return ResponseResult.error(ErrorCodeEnum.BAD_REQUEST.getCode(), e.getMessage());
    }
}
