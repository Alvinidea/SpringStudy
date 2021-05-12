package com.alvin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前端参数
        String method = req.getParameter("method");
        if(method.equals("add")) // http://localhost:8080/hello?method=add
            req.getSession().setAttribute("msg", "执行了add方法");

        if(method.equals("del")) // http://localhost:8080/hello?method=del
            req.getSession().setAttribute("msg", "执行了del方法");
        // 调用业务层
        // 视图转发 + 重定向
        req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
