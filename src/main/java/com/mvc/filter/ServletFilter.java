package com.mvc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @ClassName ServletFilter
 * @Description 过滤处理
 * @Author wangjian
 * @Date 2020/4/19 3:21 下午
 * @Version 1.0
 **/
@WebFilter(filterName = "servletFilter"
        , value = "/*" // 过滤所有路径
        , initParams = {@WebInitParam(name = "name", value = "过滤器")}
)
public class ServletFilter extends HttpFilter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        Enumeration<String> parameterNames = config.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            System.out.println(name);
            System.out.println(config.getFilterName() + ":" + config.getInitParameter(name));
        }
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.err.println("**************************************");
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, res);
    }
}
