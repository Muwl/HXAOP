package com.hxsd.online.hxaop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * 作者：MuWenLei
 * 时间：2018/6/5 下午 12:49
 * 邮箱：itexplorer@126.com
 * 说明：自动抛出异常的注解
 */
@Target({METHOD})
@Retention(CLASS)
public @interface Safe {
}
