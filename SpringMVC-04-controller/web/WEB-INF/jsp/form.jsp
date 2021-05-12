<%--
  Created by IntelliJ IDEA.
  User: Chaofan
  Date: 2021/5/12
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<br/>
展示乱码的情况 Post方式：
【出现乱码】
<br/>
 <form action="/e1/t2" method="post">
     <input type="text" name="name"/>
     <input type="submit">
 </form>
<br/>
<br/>
展示乱码的情况 Get方式：
【不会出现乱码】
<br/>
<form action="/e1/t2" method="get">
    <input type="text" name="name"/>
    <input type="submit">
</form>
<br/>
配置过滤器 public class EncodeFilter implements Filter，使用POST提交
【不会出现乱码】
<br/>
<form action="/e1/t3" method="post">
     <input type="text" name="name"/>
     <input type="submit">
 </form>
<br/>
配置过滤器 public class EncodeFilter implements Filter，使用GET提交
【不会出现乱码】
<br/>
<form action="/e1/t4" method="get">
    <input type="text" name="name"/>
    <input type="submit">
</form>
<br/>
配置SpringMVC提供的过滤器 org.springframework.web.filter.CharacterEncodingFilter，使用POST提交
【不会出现乱码】
<br/>
<form action="/e1/t4" method="post">
    <input type="text" name="name"/>
    <input type="submit">
</form>
<br/>
配置SpringMVC提供的过滤器 org.springframework.web.filter.CharacterEncodingFilter，使用GET提交
【不会出现乱码】
<br/>
<form action="/e1/t4" method="get">
    <input type="text" name="name"/>
    <input type="submit">
</form>
<hr/>
get方式提交的包含中文的串，不会出现乱码
<hr/>
post方式提交的包含中文的串，会出现乱码；所以需要配置过滤器
<pre>
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</pre>

</body>
</html>
