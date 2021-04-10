package com.javitoro.superheroes.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogExecutionAspect.class);

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long finalTime = System.currentTimeMillis() - startTime;
        String message = joinPoint.getSignature() + " has been executed in " + finalTime + "ms.";
        logger.info(message);
        return proceed;
    }
}
