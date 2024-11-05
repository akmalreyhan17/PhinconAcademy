package com.example.session7;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CachingAspect {
    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    @Around("@annotation(com.example.session7.Cached)")
    public Object cacheResult(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toShortString();
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        Object result = joinPoint.proceed();
        cache.put(key, result);
        return result;
    }
}


