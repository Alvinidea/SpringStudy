package com.alvin.controller;

import com.alvin.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 对象用于接收参数
 */
@Controller
@RequestMapping("/user")
public class UserController {

    // http://localhost:8080/user/t1?name=hahah
    @GetMapping("/t1")
    public String t1(String name, Model model){
        System.out.println("接受前端参数："+ name);
        model.addAttribute("msg", name);
        return "test";
    }
    /***
     * @RequestParam("username") 必须使用 username 接收
     * @param name 接收前端传送的参数
     * @param model 发送给前端的参数
     * @return
     */
    // http://localhost:8080/user/t1?username=hahah
    @GetMapping("/t2")
    public String t2(@RequestParam("username") String name, Model model){
        System.out.println("接受前端参数："+ name);
        model.addAttribute("msg", name);
        return "test";
    }

    /**
     * 属性名字需要一致，顺序没有关系
     * http://localhost:8080/user/t3?id=1&name=alvin&age=18
     * http://localhost:8080/user/t3?name=alvin&id=1&age=18
     *      User(id=1, name=alvin, age=18)
     *
     * http://localhost:8080/user/t3?nasasme=alvin&id=1&age=18
     *      User(id=1, name=null, age=18)
     * http://localhost:8080/user/t3?nme=alvin&ids=1&agse=18
     *      User(id=0, name=null, age=0)
     * 使用对象接收参数
     * @param user
     * @return
     */
    @GetMapping("/t3")
    public String t3(User user){
        System.out.println(user);
        System.out.println("使用对象接收参数");
        return "test";
    }
}
