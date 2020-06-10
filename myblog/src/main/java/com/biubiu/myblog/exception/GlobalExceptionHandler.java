package com.biubiu.myblog.exception;

import com.biubiu.myblog.util.RestResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一全局异常处理
 *
 * @author wbbaijq
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse handlerException(MethodArgumentNotValidException e) {
        return RestResponse.failure(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public RestResponse handlerException(Exception e) {
        return RestResponse.failure("未知异常！原因是: " + e.getMessage());
    }
}
