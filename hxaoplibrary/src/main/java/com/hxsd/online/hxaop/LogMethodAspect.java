package com.hxsd.online.hxaop;

import android.util.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

/**
 * 作者：MuWenLei
 * 时间：2018/6/5 下午 12:45
 * 邮箱：itexplorer@126.com
 * 说明：Log的的切点实现
 */
@Aspect
public class LogMethodAspect {
    private static String TAG="LogMethodAspect";

    @Around("execution(!synthetic * *(..)) && onLogMethod()")
    public Object doLogMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return logMethod(joinPoint);
    }

    @Pointcut("@within(com.hxsd.online.hxaop.annotation.LogMethod)||@annotation(com.hxsd.online.hxaop.annotation.LogMethod)")
    public void onLogMethod() {
    }

    public Object logMethod(final ProceedingJoinPoint joinPoint) throws Throwable {

        Log.i(TAG,joinPoint.getSignature().toShortString() + " Args : " + (joinPoint.getArgs() != null ? Arrays.deepToString(joinPoint.getArgs()) : ""));
        Object result = joinPoint.proceed();
        String type = ((MethodSignature) joinPoint.getSignature()).getReturnType().toString();
        Log.i(TAG,joinPoint.getSignature().toShortString() + " Result : " + ("void".equalsIgnoreCase(type)?"void":result));
        return result;
    }
}
