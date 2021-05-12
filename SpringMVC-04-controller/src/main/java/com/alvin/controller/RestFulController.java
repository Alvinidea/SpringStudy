package com.alvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestFulController {

    @RequestMapping("add1/{a}/{b}")
    public String test1(@PathVariable int a, @PathVariable int b, Model model)
    {
        int ret = a + b;
        model.addAttribute("msg","Output : " + ret);
        return "test";
    }

    @RequestMapping(name = "add/{a}/{b}", method= RequestMethod.DELETE)
    public String test2(@PathVariable int a, @PathVariable int b, Model model)
    {
        int ret = a + b;
        model.addAttribute("msg","Output : " + ret);
        return "test";
    }
}
