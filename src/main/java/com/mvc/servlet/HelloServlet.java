package com.mvc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName HelloServlet
 * @Description TODO
 * @Author wangjian
 * @Date 2020/4/19 2:58 下午
 * @Version 1.0
 **/
@WebServlet( name = "helloServlet", // 名字
        urlPatterns = {"/hello.action", "/hello/servlet"}, // 访问的连接
        loadOnStartup = 1, // 启动加载顺序
        initParams = {@WebInitParam(name = "name", value = "小明")})
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("<h1>Hello Servlet</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("【helloServlet】初始化内容:" + config.getServletName() + ":" + config.getInitParameter("name"));
    }
}
