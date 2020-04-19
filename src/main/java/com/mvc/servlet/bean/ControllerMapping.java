package com.mvc.servlet.bean;

import java.lang.reflect.Method;

/**
 * @ClassName ControllerMapping
 * @Description 定义要执行的指定类与方法
 * @Author wangjian
 * @Date 2020/4/20 2:48 上午
 * @Version 1.0
 **/
public class ControllerMapping {

    // 使用了@Controller的注解类
    private Class<?> clazz;
    // 在使用了@Controller注解类中使用了@RequestMapping方法
    private Method method;

    public ControllerMapping(Class<?> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Method getMethod() {
        return method;
    }
}
