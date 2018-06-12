package com.hxsd.online.hxaop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

/**
 * 作者：MuWenLei
 * 时间：2018/6/5 下午 12:43
 * 邮箱：itexplorer@126.com
 * 说明：消耗时间注解
 */
@Target({METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Trace {

    boolean enable() default true;
}
