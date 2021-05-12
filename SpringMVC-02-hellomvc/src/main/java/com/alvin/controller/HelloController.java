package com.alvin.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 实现 Controller 接口
public class HelloController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ModelAndView 模型视图对象
        ModelAndView mv = new ModelAndView();
        // 封装对象，放置到 mv 中
        mv.addObject("msg", "HelloSpringMVC");
        // /WEB-INF/jsp/hello.jsp
        mv.setViewName("hello");
        return mv;
    }
}
