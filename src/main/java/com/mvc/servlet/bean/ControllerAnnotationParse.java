package com.mvc.servlet.bean;

import com.mvc.annotation.Controller;
import com.mvc.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ControllerAnnotationParse
 * @Description 控制器层注解类解析
 * @Author wangjian
 * @Date 2020/4/20 2:53 上午
 * @Version 1.0
 **/
public class ControllerAnnotationParse {

    private static final Map<String, ControllerMapping> MAP_PARSE = new HashMap<>();
    // controller类中的路径
    private String parentPath;
    // 要解析注解的类
    private Class<?> clazz;

    public ControllerAnnotationParse(Class<?> clazz) {
        this.clazz = clazz;
        parseControllerHandel();
    }

    /*
    *
     * @Author wangjian
     * @Description  解析
     * @Date 2:57 上午 2020/4/20
     * @Param clazz:
     * @return void
     **/
    private void parseControllerHandel() {
        Controller controller = this.clazz.getAnnotation(Controller.class);
        if (controller == null) { // 不存在 controller 注解直接返回
            return;
        }
        RequestMapping requestMapping = this.clazz.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            // 获取父路径
            this.parentPath = requestMapping.name();
        }
        // 获取当前类中的所有方法
        Method[] methods = this.clazz.getMethods();
        for (Method method : methods) {
            parseRequestMappingHandel(method);
        }
    }

    /*
    *
     * @Author wangjian
     * @Description  解析当前类方法中的所有注解
     * @Date 3:07 上午 2020/4/20
     * @Param :
     * @return void
     **/
    private void parseRequestMappingHandel(Method method) {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping == null) {
            return;
        } else {
            String methodPath = requestMapping.name();
            String parentPath = this.getParentPath();
            if (parentPath.substring(parentPath.length()-1).indexOf("/") == -1) {
                // 父路径缺少分隔符
                parentPath = parentPath +"/";
            }
            if (methodPath.substring(0, 1).indexOf("/") != -1) {
                methodPath = methodPath.substring(1, methodPath.length());
            }
            // 最终会被执行方法的对象
            ControllerMapping mapping = new ControllerMapping(this.clazz, method);
            MAP_PARSE.put(parentPath+methodPath, mapping);
        }
    }


    private String getParentPath() {
        return parentPath;
    }

    public Map<String, ControllerMapping> getMapParse() {
        return MAP_PARSE;
    }


}
