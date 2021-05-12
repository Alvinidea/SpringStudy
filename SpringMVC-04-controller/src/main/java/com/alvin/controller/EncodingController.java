package com.alvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 乱码问题 ：
 *      get方式提交时候，中文不会出现乱码
 *      post方式提交时候，中文会出现乱码
 */
@Controller
public class EncodingController {
    @RequestMapping("/e1/t1")
    public String t1()
    {
        return "form";
    }

    // 前端输入：Alvin炒饭
    @RequestMapping(value = "/e1/t2", method = RequestMethod.GET)
    public String t1(Model model, String name)
    {
        System.out.println(name); // 打印结果： Alvin炒饭
        model.addAttribute("msg", name);
        return "test";
        // 前端页面显示结果： Alvin炒饭
    }

    // 前端输入：Alvin炒饭
    @RequestMapping(value = "/e1/t2", method = RequestMethod.POST)
    public String t2(Model model, String name)
    {
        System.out.println(name); // 打印结果： Alvin???é??
        model.addAttribute("msg", name);
        return "test";
        // 前端页面显示结果： Alvinçé¥­
    }

    /***
     * 简 单 的  配置了过滤器之后，使用Post方式提交，出现乱码
     *  1. public class EncodeFilter implements Filter {...}
     *  2. <filter>
     *         <filter-name>encoding</filter-name>
     *         <filter-class>com.alvin.filter.EncodeFilter</filter-class>
     *     </filter>
     *     <filter-mapping>
     *         <filter-name>encoding</filter-name>
     *         <url-pattern>/*</url-pattern>
     *     </filter-mapping>
     * @param model
     * @param name
     * @return
     */
    // 前端输入：Alvin炒饭
    @RequestMapping(value = "/e1/t3", method = RequestMethod.POST)
    public String t3(Model model, String name)
    {
        System.out.println(name); // 打印结果： Alvin炒饭
        model.addAttribute("msg", name);
        return "test";
        // 前端页面显示结果： Alvin炒饭
    }

    /***
     * 配置了过滤器之后，使用Get方式提交，未出现乱码
     * @param model
     * @param name
     * @return
     */
    // 前端输入：Alvin炒饭
    @RequestMapping(value = "/e1/t3", method = RequestMethod.GET)
    public String t31(Model model, String name)
    {
        System.out.println(name); // 打印结果： Alvin炒饭
        model.addAttribute("msg", name);
        return "test";
        // 前端页面显示结果： Alvin炒饭
    }

    /***
     * 使用SpringMVC提供的过滤器类
     *     <filter>
     *         <filter-name>encoding</filter-name>
     *         <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
     *         <init-param>
     *             <param-name>encoding</param-name>
     *             <param-value>utf-8</param-value>
     *         </init-param>
     *     </filter>
     *     <filter-mapping>
     *         <filter-name>encoding</filter-name>
     *         <url-pattern>/*</url-pattern>
     *     </filter-mapping>
     * @param model
     * @param name
     * @return
     */

    // 前端输入：Alvin炒饭
    @RequestMapping(value = "/e1/t4", method = RequestMethod.POST)
    public String t4(Model model, String name)
    {
        System.out.println(name); // 打印结果： Alvin???é??
        model.addAttribute("msg", name);
        return "test";
        // 前端页面显示结果： Alvinçé¥­
    }

    /***
     * 配置了过滤器之后，使用Get方式提交，未出现乱码
     * @param model
     * @param name
     * @return
     */
    // 前端输入：Alvin炒饭
    @RequestMapping(value = "/e1/t4", method = RequestMethod.GET)
    public String t41(Model model, String name)
    {
        System.out.println(name); // 打印结果： Alvin炒饭
        model.addAttribute("msg", name);
        return "test";
        // 前端页面显示结果： Alvin炒饭
    }
}
