package com.gua.guaaop.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SysLogAspect {

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.gua.guaaop.aop.RequestLog)")
    public void logPoinCut() {

    }

    @AfterThrowing(throwing = "throwable",pointcut = "logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint,Throwable throwable) {
        requestParams(joinPoint);

        String message = throwable.getMessage();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        String stackTraceStr = JSON.toJSONString(stackTrace);
        String resultStr = message+stackTraceStr;
        String result = resultStr.substring(0, 256);
    }

    //切面 配置通知
    @AfterReturning(returning = "result",pointcut = "logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint,Object result) {
        requestParams(joinPoint);

        String resultStr = result.toString();
        String re = resultStr.substring(0,2);
    }

    private void requestParams(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        String methodName = method.getName();
        Object[] args = joinPoint.getArgs();
        String argsStr = JSON.toJSONString(args);
        int index = argsStr.indexOf("code");
        String keyparmas = argsStr.substring(index, index + 10);
    }
}