package com.hxsd.online.hxaop;

import android.text.TextUtils;
import android.util.Log;


import com.hxsd.online.hxaop.annotation.Trace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 作者：MuWenLei
 * 时间：2018/6/5 下午 12:43
 * 邮箱：itexplorer@126.com
 * 说明：检测方法执行时间切点实现
 */
@Aspect
public class TraceAspect {
    private static String TAG="TraceAspect";

    private static final String POINTCUT_METHOD = "execution(@com.hxsd.online.hxaop.annotation.Trace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.hxsd.online.hxaop.annotation.Trace *.new(..))";

    private static final int ns = 1000*1000;

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithTrace() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedTrace() {
    }

    @Around("methodAnnotatedWithTrace() || constructorAnnotatedTrace()")
    public Object traceMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Trace trace =joinPoint.getThis().getClass().getAnnotation(Trace.class);
        if (trace!=null && !trace.enable()) {
            return joinPoint.proceed();
        }
        String className =signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        if (TextUtils.isEmpty(className)) {
            className = "Anonymous class";
        }
        Log.i(TAG,buildLogMessage(className,methodName, stopWatch.getElapsedTime()));

        return result;
    }

    /**
     * Create a log message.
     *
     * @param methodName A string with the method name.
     * @param methodDuration Duration of the method in milliseconds.
     * @return A string representing message.
     */
    public static String buildLogMessage(String className,String methodName, long methodDuration) {

        if (methodDuration > 10 * ns) {
            return String.format("%s---%s() take %d ms", className,methodName, methodDuration / ns);
        } else if (methodDuration > ns) {
            return String.format("%s---%s() take %dms %dns", className,methodName, methodDuration / ns,
                    methodDuration % ns);
        } else {
            return String.format("%s---%s() take %dns", className,methodName, methodDuration % ns);
        }
    }
}
