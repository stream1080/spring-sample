package com.example.demo.filter;


import com.example.demo.exception.UserLoginException;
import com.example.demo.model.entity.DemoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author stream
 */
@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * true表示继续，false中断
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("sessionId= {}", request.getSession());
        DemoUser user = (DemoUser) request.getSession().getAttribute("CURRENT_USER");
        if (user == null) {
            log.info("user = null");
            throw new UserLoginException();
        }
        return true;
    }

}
