package com.hxsd.online.hxaop;

import android.util.Log;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 作者：MuWenLei
 * 时间：2018/6/5 下午 12:51
 * 邮箱：itexplorer@126.com
 * 说明：自动抛出异常的注解切点的实现
 */
@Aspect
public class SafeAspect {
    private static String TAG="SafeAspect";

    @Around("execution(!synthetic * *(..)) && onSafe()")
    public Object doSafeMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        return safeMethod(joinPoint);
    }

    @Pointcut("@within(com.hxsd.online.hxaop.annotation.Safe)||@annotation(com.hxsd.online.hxaop.annotation.Safe)")
    public void onSafe() {
    }

    public Object safeMethod(final ProceedingJoinPoint joinPoint) {

        Object result = null;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            Log.w(TAG,getStringFromException(e));
        }
        return result;
    }

    public static String getStringFromException(Throwable ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
