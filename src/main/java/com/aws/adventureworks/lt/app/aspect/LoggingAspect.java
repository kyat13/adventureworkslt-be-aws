package com.aws.adventureworks.lt.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.aws.adventureworks.lt.app.customer..*(..)))")
    public Object logMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String methodName = methodSignature.getMethod().getName();
        String [] paramNames = methodSignature.getParameterNames();
        Class<?> [] paramTypes = methodSignature.getMethod().getParameterTypes();
        Object[] paramValues = proceedingJoinPoint.getArgs();

        String params = IntStream.of(0, Math.min(methodSignature.getParameterNames().length -1 , methodSignature.getParameterTypes().length -1 ))
                .mapToObj( i -> MessageFormat.format("<{0}>{1}:{2}",  paramTypes[i], paramNames[i], paramValues[i].toString()))
                .collect(Collectors.joining(","));
        log.info("{}.{} {}", proceedingJoinPoint.getTarget().getClass().getSimpleName(), methodName, params);
        return proceedingJoinPoint.proceed();
    }
}
