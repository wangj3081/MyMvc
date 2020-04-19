package com.mvc.controller;

import com.mvc.annotation.Controller;
import com.mvc.annotation.RequestMapping;

/**
 * @ClassName MemberAction
 * @Description TODO
 * @Author wangjian
 * @Date 2020/4/20 3:45 上午
 * @Version 1.0
 **/
@Controller
@RequestMapping(name = "/member")
public class MemberAction {

    @RequestMapping(name = "add")
    public void addMember() {
        System.out.println("【member】执行了 addMember 方法");
    }

    @RequestMapping(name = "delete")
    public void deleteMember() {
        System.out.println("【member】执行了 deleteMember 方法");
    }
}
