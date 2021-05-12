package com.alvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/h1")
    public String hello(Model model)
    {
        // 数据封装
        model.addAttribute("msg","Hello, Annotation");
        // /WEB-INF/jsp/hello.jsp
        return "hello";
    }
}
