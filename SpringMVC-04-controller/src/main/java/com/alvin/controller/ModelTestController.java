package com.alvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/***
 *  对重定向和转发进行测试
 *  1. 配置视图解析器时候的转发和重定向
 *  2. 未配置视图解析器时候的转发和重定向
 *  3. HttpServletRequest request, HttpServletResponse response的转发和重定向
 *
 *  https://zhuanlan.zhihu.com/p/185839916
 */
@Controller
public class ModelTestController {


    /**
     * 普通方式 进行转发
     * @return
     */
    @RequestMapping("/m1/t1")
    public String m1t1(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        return "test";
    }

    /**
     * 如：当前访问的地址为： http://localhost:8080/m1/t1
     * 经过重定向之后，地址变为：http://localhost:8080/index.jsp
     * @return
     */
    // 重定向：会修改 url，所以redirect之后直接写完整地址:index.jsp
    @RequestMapping("/m1/t2")
    public String m1t2()
    {

        System.out.println("/m1/t2");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/m1/t3") // 失败
    public String m1t3()
    {// 失败
        System.out.println("/m1/t3");
        return "redirect:/WEB-INF/jsp/redirect.jsp"; // HTTP状态 404 - 未找到
    }
    @RequestMapping("/m1/t30") // 失败
    public String m1t30()
    {// 失败
        System.out.println("/m1/t30");
        return "redirect:/jsp/redirect.jsp"; // HTTP状态 404 - 未找到
    }
    @RequestMapping("/m1/t31") // 失败
    public String m1t31()
    {// 失败
        System.out.println("/m1/t31");
        return "redirect:/redirect.jsp"; // HTTP状态 404 - 未找到
    }
    @RequestMapping("/m1/t32") // 失败
    public String m1t32()
    {// 失败
        System.out.println("/m1/t32");
        return "redirect:redirect.jsp"; // HTTP状态 404 - 未找到
    }
    @RequestMapping("/m1/t4")
    public String m1t4()
    {
        System.out.println("/m1/t4");
        return "redirect:/m1/t1";
    }

    /** ==============================================================================
     * 以下代码是注释掉视图解析器之后（就是注释掉springmvc-servlet.xml中的以下代码）
     * <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="internalResourceViewResolver">
     *         <property name="prefix" value="/WEB-INF/jsp/"/>
     *         <property name="suffix" value=".jsp"/>
     *     </bean>-->
     *   使用此方式依然可以 转发 和 重定向
     * @param model
     * @return
     */
    // 转发：方式 1
    @RequestMapping("/m2/t1")
    public String m2t1(Model model)
    {
        model.addAttribute("msg",
                "注释掉视图解析器之后 return \"/WEB-INF/jsp/test.jsp\";");
        return "/WEB-INF/jsp/test.jsp";
    }

    // 转发：方式 2
    @RequestMapping("/m2/t2")
    public String m2t2(Model model)
    {
        model.addAttribute("msg",
                "注释掉视图解析器之后 return \"forward:/WEB-INF/jsp/test.jsp\";");
        return "forward:/WEB-INF/jsp/test.jsp";
    }

    // 重定向
    @RequestMapping("/m2/t3")
    public String m2t3(Model model)
    {
        model.addAttribute("msg", "注释掉视图解析器之后  return \"redirect:/index.jsp\";");
        return "redirect:/index.jsp";
    }

    // 重定向
    @RequestMapping("/m2/t4")
    public String m2t4(Model model)
    {// 失败
        model.addAttribute("msg", "注释掉视图解析器之后  return \"redirect:/index.jsp\";");
        return "redirect:/WEB-INF/jsp/test.jsp"; // HTTP状态 404 - 未找到
    }

    // 重定向
    @RequestMapping("/m2/t5")
    public String m2t5(Model model)
    {// 失败
        model.addAttribute("msg", "注释掉视图解析器之后  return \"redirect:/index.jsp\";");
        return "redirect:/m1/t1"; // HTTP状态 404 - 未找到；
        // 视图解析其都没了 /m1/t1 的前缀后缀不会进行连接了
    }

    /*** ============================================================================
     * 使用 HttpServletRequest request, HttpServletResponse response
     * 进行转发和重定向
     * 这种方式与 视图解析器无关（意思是说：配不配置都没有影响）
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/m0/t1")
    public void m0t1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("使用HttpServletResponse");
    }

    @RequestMapping("/m0/t2")
    public void m0t2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/index.jsp");
    }

    @RequestMapping("/m0/t3")
    public void m0t3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/WEB-INF/jsp/test.jsp");// HTTP状态 404 - 未找到
    }

    @RequestMapping("/m0/t31")
    public void m0t31(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/m0/t1");
    }

    @RequestMapping("/m0/t4")
    public void m0t4(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("msg", "/m0/t4");
        request.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(request,response);
    }

    @RequestMapping("/m0/t5")
    public void m0t5(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("msg", "/m0/t5");
        request.getRequestDispatcher("/m0/t1").forward(request,response);
    }
}
