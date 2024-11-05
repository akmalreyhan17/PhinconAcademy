package com.example.session7;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
    @Before("@annotation(com.example.session7.Validated) && args(input)")
    public void validateData(JoinPoint joinPoint, Object input) throws IllegalArgumentException {
        if (input == null || (input instanceof String && ((String) input).isEmpty())) {
            throw new IllegalArgumentException("Input data is invalid.");
        }
        System.out.println("Data validated for: " + joinPoint.getSignature().getName());
    }
}


