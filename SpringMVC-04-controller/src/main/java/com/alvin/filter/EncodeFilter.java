package com.alvin.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 实现过滤器：用于uft-8编码配置
 * 需要在 web.xml 中进行配置
 *     <filter>
 *         <filter-name>encoding</filter-name>
 *         <filter-class>com.alvin.filter.EncodeFilter</filter-class>
 *     </filter>
 *     <filter-mapping>
 *         <filter-name>encoding</filter-name>
 *         <url-pattern>/</url-pattern>
 *     </filter-mapping>
 *
 *
 */
public class EncodeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
