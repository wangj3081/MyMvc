package com.mvc.controller;

import com.mvc.annotation.Controller;
import com.mvc.annotation.RequestMapping;

/**
 * @ClassName DepaAction
 * @Description TODO
 * @Author wangjian
 * @Date 2020/4/19 4:27 下午
 * @Version 1.0
 **/
@Controller(name = "deptController")
@RequestMapping(name = "/dept")
public class DeptAction {


    @RequestMapping(name = "/add")
    public String addDept() {
        System.out.println("【DEPT】执行 ADD 方法");
        return "Add Dept";
    }
}
