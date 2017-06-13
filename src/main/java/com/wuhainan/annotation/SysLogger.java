package com.wuhainan.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * <br>Created by Admin on 2017/5/5.
 * <br>星期五 at 16:29.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogger {
    String value() default "";
}
