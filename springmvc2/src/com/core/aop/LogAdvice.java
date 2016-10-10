package com.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

import java.util.logging.Logger;

/**
 * Created by 45595 on 2016/9/1.
 */
public class LogAdvice {
    /**
     * 创建日志对象
     */
    private Logger logger = Logger.getLogger(LogAdvice.class.getName());
    /** 定义开始的毫秒数 */
    private long beginMillis;
    /** 定义结束的毫秒数 */
    private long endMillis;

    /** 在调用业务层方法之前 */
    @Before("execution(* com.newSpring.service.*.*.*(..))")
    public void invokeBefore(JoinPoint joinPoint) {
        logger.info("开始调用的方法:" + joinPoint.getSignature().getName());
        beginMillis = System.currentTimeMillis();
        logger.info("开始调用["+joinPoint.getSignature().getName()+"]的毫秒数"+beginMillis);
        /*logger.info("开始调用的方法：" + joinPoint.getSignature().getName());
        beginMillis = System.currentTimeMillis();
        logger.info("开始调用【" + joinPoint.getSignature().getName() + "】的毫秒数：" + beginMillis);*/
    }
    /** 在调用业务层方法之后 */
    @AfterReturning(pointcut="execution(* com.newSpring.service.*.*.*(..))", returning="res")
    public void invokeAfter(JoinPoint joinPoint, Object res){
        logger.info("结束调用的方法:"+joinPoint.getSignature().getName());
        endMillis = System.currentTimeMillis();
        logger.info("调用该[" + joinPoint.getSignature().getName() + "]方法,一共花费了:" + (endMillis - beginMillis) + "毫秒");
        logger.info("调用该[" + joinPoint.getSignature().getName() + "]方法,返回值为:" + res);
        /*logger.info("结束调用的方法：" + joinPoint.getSignature().getName());
        endMillis =  System.currentTimeMillis();
        logger.info("调用该【"+ joinPoint.getSignature().getName()  +"】方法，一共花费了：" + (endMillis - beginMillis) + "毫秒");
        logger.info("调用该【"+ joinPoint.getSignature().getName()  +"】方法，返回值为：" + res);*/
    }


    /** 记录业务层异常的日志信息 */
    @AfterThrowing(pointcut="execution(* com.newSpring.service.*.*.*(..))", throwing="ex")
    public void error(JoinPoint joinPoint, Throwable ex){
        logger.info("调用该【"+ joinPoint.getSignature().getName()  +"】方法，出现了异常！");
        logger.log(java.util.logging.Level.WARNING,ex.getMessage());
    }
}
