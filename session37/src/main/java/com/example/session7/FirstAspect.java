package com.example.session7;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class FirstAspect {
    @Before("execution(* com.example.service.*.*(..))")
    public void logFirst(JoinPoint joinPoint) {
        System.out.println("First aspect: " + joinPoint.getSignature().getName());
    }
}


