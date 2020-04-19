package com.mvc.annotation;

import java.lang.annotation.*;

/**
 * @ClassName RequestMapping
 * @Description TODO
 * @Author wangjian
 * @Date 2020/4/19 3:45 下午
 * @Version 1.0
 **/
@Documented // 文档
@Target({ElementType.TYPE, ElementType.METHOD}) // 可用于所有结构之中(类),方法之中
@Retention(RetentionPolicy.RUNTIME) // 在运行时扫描
public @interface RequestMapping {
    // 名称
    public String name();
    // 运行方法
    String method() default "GET";
}
