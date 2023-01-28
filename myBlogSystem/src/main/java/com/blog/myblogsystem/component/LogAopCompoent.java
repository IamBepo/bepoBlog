package com.blog.myblogsystem.component;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAopCompoent {
    @Before("execution(public * com.blog.myblogsystem.web.*.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object arg : args) {
            params.append(arg).append(" ");
        }
        log.info(className + "#" + methodName + "[入参]：" + params.toString());
    }

    @Around("execution(public * com.blog.myblogsystem.web.*.*.*(..))")
    public Object catchException(ProceedingJoinPoint joinPoint) {
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        }catch (Throwable e) {
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            log.error("异常出现：" + className + "的" + methodName + ": " + e);
            //TODO 未处理返回
            return e;
        }
    }

    @AfterReturning(value = "execution(public * com.blog.myblogsystem.web.*.*.*(..))",returning = "returnVal")
    public void afterReturn(JoinPoint joinPoint,Object returnVal) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info(className + "#" + methodName + "[返回值]：" + JSONUtils.toJSONString(returnVal));
    }
}
