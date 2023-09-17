package com.example.demo.annnotation;

import java.lang.annotation.*;

/**
 * body 加密竹节,针对类和方法
 *
 * @date 2023-09-06 15:55:00
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseEncrypt {

    /**
     * 对 body 加密，默认是true
     *
     * @return boolean
     */
    boolean value() default true;
}