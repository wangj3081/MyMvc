package com.mvc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName ServletListener
 * @Description TODO
 * @Author wangjian
 * @Date 2020/4/19 3:34 下午
 * @Version 1.0
 **/
@WebListener(value = "listener")
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("***********************【容器初始化】服务监听***********************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.err.println("【listener】：Tomcat 关闭，容器销毁" );
    }
}
