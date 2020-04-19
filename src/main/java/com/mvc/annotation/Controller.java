package com.mvc.annotation;

import java.lang.annotation.*;

/**
 * @ClassName Controller
 * @Description TODO
 * @Author wangjian
 * @Date 2020/4/19 3:44 下午
 * @Version 1.0
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // 运行时扫描
public @interface Controller {
   public String name() default "none";
}
