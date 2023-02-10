package com.example.demo.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 拦截器
 *
 * @author stream
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        String traceId = UUID.randomUUID().toString().replace("-", "");
        log.info("traceId: {}  nowTime: {}, URL: {}", traceId, LocalDateTime.now(), request.getRequestURL());
        request.setAttribute("startTime", startTime);
        request.setAttribute("traceId", traceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object traceId = request.getAttribute("traceId");
        log.info("traceId: {}  nowTime: {}, URL: {}", traceId, LocalDateTime.now(), request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        Object traceId = request.getAttribute("traceId");
        log.info("traceId: {}  nowTime: {}, URL: {}, timeTaken: {}", traceId, LocalDateTime.now(), request.getRequestURL(), time);
    }
}
