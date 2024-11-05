package com.example.session7;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class SecondAspect {
    @Before("execution(* com.example.service.*.*(..))")
    public void logSecond(JoinPoint joinPoint) {
        System.out.println("Second aspect: " + joinPoint.getSignature().getName());
    }
}


