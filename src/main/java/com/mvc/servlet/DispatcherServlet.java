package com.mvc.servlet;

import com.mvc.annotation.scan.ScanPackageUtil;
import com.mvc.servlet.bean.ControllerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName DispacherServlet
 * @Description 处理所有请求的 Servlet
 * @Author wangjian
 * @Date 2020/4/19 4:31 下午
 * @Version 1.0
 **/
@WebServlet( name = "dispatchServlet", // 名字
        urlPatterns = {"*.action"}, // 访问的连接
        loadOnStartup = 1, // 启动加载顺序
        initParams = {@WebInitParam(name = "path", value = "com.mvc.controller")})
public class DispatcherServlet extends HttpServlet {

    /*
    *
     * @Author wangjian
     * @Description  扫描所有控制层的包与类
     * @Date 4:33 下午 2020/4/19
     * @Param config: 
     * @return void
     **/
    @Override
    public void init(ServletConfig config) throws ServletException {
        String path = config.getInitParameter("path");
        ScanPackageUtil.scanPackage(this.getClass(), path);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        // 获得路径内容
        servletPath = servletPath.substring(0, servletPath.lastIndexOf(".action"));
        ControllerMapping mapping = ScanPackageUtil.getMapPath().get(servletPath);
        try {
            Object object = mapping.getClazz().getDeclaredConstructor().newInstance();
            // 执行对应方法内容
            Object invoke = mapping.getMethod().invoke(object);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
