package com.example.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component("logger")
@Aspect //表示当前类是一个切面类
@EnableAspectJAutoProxy
public class LogAspect {

    private Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    public void pt1(){

    }

    @Around("pt1()")
    public Object Around(ProceedingJoinPoint joinPoint){
        Object returnValue = null;
        try {
            Object[] args = joinPoint.getArgs();

            log.info("前置通知");
            returnValue = joinPoint.proceed(args);
            log.info("后置通知");
            return  returnValue;
        } catch (Throwable throwable) {
            log.error("异常通知");
            throw new RuntimeException(throwable);

        }finally {
            log.info("最终通知");
        }
    }

//    @Before("pt1()")
//    public void beforePointcut(){
//
//    }
//    @AfterReturning("pt1()")
//    public void AfterReturningPointcut(){
//
//    }
//    @AfterThrowing("pt1()")
//    public void AfterThrowingPointcut(){
//
//    }
//    @After("pt1()")
//    public void AfterPointcut(){
//
//    }


}
