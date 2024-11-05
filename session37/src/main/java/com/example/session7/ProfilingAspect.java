package com.example.session7;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ProfilingAspect {
    @Around("@annotation(com.example.session7.TrackTime)")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Method " + joinPoint.getSignature().getName() + " executed in " + elapsedTime + " ms");
        return result;
    }
}


