package com.young.zhang.exception;

import com.young.zhang.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.erro(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "Something wrong, please contact IT for details.");
    }
}
