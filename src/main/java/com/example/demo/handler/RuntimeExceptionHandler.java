package com.example.demo.handler;


import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.UserLoginException;
import com.example.demo.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 异常处理
 *
 * @author stream
 */
@Slf4j
@RestControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseVo runtimeException(RuntimeException e) {
        log.error("======> {}", e.getMessage());
        return ResponseVo.error(ResponseEnum.SERVER_ERROR, e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(com.example.demo.exception.UserLoginException.class)
    public ResponseVo userLoginException(UserLoginException e) {
        log.error("======> {}", e.getMessage());
        return ResponseVo.error(ResponseEnum.FORBIDDEN);
    }

    @ExceptionHandler(BindException.class)
    public ResponseVo methodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到 ObjectError 对象
        log.error("======> {}", e.getMessage());
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return ResponseVo.error(ResponseEnum.INVALID_PARAM, objectError.getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.error("======> {}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        return ResponseVo.error(ResponseEnum.INVALID_PARAM,
                Objects.requireNonNull(bindingResult.getFieldError()).getField() + " - " + bindingResult.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVo exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("======> {}", e.getMessage());
        return ResponseVo.error(ResponseEnum.SERVER_ERROR, e.getMessage());
    }
}
