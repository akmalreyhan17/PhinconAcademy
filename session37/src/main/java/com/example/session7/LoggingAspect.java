package com.example.session7;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.service.*.*(..))")
    public void serviceMethods() {}

    @Pointcut("execution(* com.example.dao.*.*(..))")
    public void daoMethods() {}

    @Before("serviceMethods() || daoMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }

    //@Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")

    @Pointcut("execution(* transfer(..))") // the pointcut expression
    private void anyOldTransfer() {} // the pointcut signature

    // @Before("anyOldTransfer()")
    // public void logBefore(JoinPoint joinPoint) {
    //     System.out.println("Before method: " + joinPoint.getSignature().getName());
    // }

    // @Before("execution(* com.example.session7.MyService.printName(..))")
    // public void logBefore(JoinPoint joinPoint) {
    //     System.out.println("Before method: " + joinPoint.getSignature().getName());
    // }

    @AfterReturning(pointcut = "execution(* com.example.session7.MyService.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method returned: " + joinPoint.getSignature().getName() + ", Result: " + result);
    }
    
    @AfterThrowing(pointcut = "execution(* com.example.session7.MyService.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("Method threw exception: " + joinPoint.getSignature().getName() + ", Exception: " + error);
    }

}



