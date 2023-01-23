package com.example.demo.aspect;

import com.example.demo.annnotation.Limit;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author stream1080
 * @date 2023-01-23 10:51:12
 */
@Slf4j
@Aspect
@Component
public class LimitAop {

    /**
     * 不同的接口，不同的流量控制
     * map的key为 Limiter.key
     */
    private final Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();

    @Around("@annotation(com.example.demo.annnotation.Limit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取 limit 的注解
        Limit limit = method.getAnnotation(Limit.class);
        if (limit != null) {
            // key 作用：不同的接口，不同的流量控制
            String key = limit.key();
            RateLimiter rateLimiter;
            // 验证缓存是否有命中 key
            if (!limitMap.containsKey(key)) {
                // 创建令牌桶
                rateLimiter = RateLimiter.create(limit.permitsPerSecond());
                limitMap.put(key, rateLimiter);
                log.info("新建了令牌桶: {}，容量: {}", key, limit.permitsPerSecond());
            }
            rateLimiter = limitMap.get(key);

            // 获取令牌
            boolean acquire = rateLimiter.tryAcquire(limit.timeout(), limit.timeunit());

            // 获取不到命令牌，直接返回异常提示
            if (!acquire) {
                log.debug("令牌桶: {}，获取令牌失败", key);
                return null;
            }
        }

        return joinPoint.proceed();
    }
}

