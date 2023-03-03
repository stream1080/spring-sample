package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

/**
 * 日志切面
 *
 * @author stream1080
 * @date 2023-03-02 14:09:17
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around("within(com.example.demo.controller..*)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 目标类的名称
        String className = joinPoint.getTarget().getClass().getName();
        // 方法名称
        String methodName = joinPoint.getSignature().getName();
        // 参数列表
        Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
        Object[] args = joinPoint.getArgs();

        StringBuilder params = new StringBuilder("[ ");
        for (int i = 0; i < parameters.length; i++) {
            params.append(parameters[i].getName()).append(":").append(args[i]).append("; ");
        }
        if (parameters.length > 0) {
            int length = params.length();
            params.delete(length - 2, length);
        }
        params.append(" ]");
        log.info("class: {} | method: {} | request: {}", className, methodName, params);
        Object result = null;
        try {
            // 执行方法
            result = joinPoint.proceed();
        } catch (Exception e) {
            // 记录异常日志
            log.error("class: {} | method: {}\n{}\n\t at {} ", className, methodName, e.toString(), e.getStackTrace()[0]);
            throw e;
        }
        // 记录方法结果
        log.info("class: {} | method: {} | response: {}", className, methodName, result);
        return result;
    }
}
