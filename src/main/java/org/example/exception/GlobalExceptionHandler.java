package org.example.exception;

import org.example.common.Result;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice//这个注解可以让全局异常处理器在Controller抛异常的时候接管
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)//标记异常对应调用的方法
    @ResponseStatus(HttpStatus.BAD_REQUEST)//返回HTTP状态码为400（Bad Request）
    public Result<?> missingParameter(Exception e) {//处理参数类问题
        e.printStackTrace();//控制台打印异常
        return new Result<>(400, "用户名和密码不能为空！", null);//返回提示性信息

    }

    @ExceptionHandler(HandlerMethodValidationException.class)//参数已传但不合法（空串、长度不符等）
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> invalidParameter(HandlerMethodValidationException e) {
        String msg = e.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("参数不合法");
        return new Result<>(400, msg, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Result<?> ex (Exception e) {
        e.printStackTrace();
        return new Result<>(500, e.getMessage(),  null);
    }
}
