package com.example.demo.config;

import com.example.demo.filter.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author stream
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                //拦截所有接口
                .addPathPatterns("/**")
                //不拦截的接口
                .excludePathPatterns("/register", "/login", "/swagger-ui/**");
    }
}
