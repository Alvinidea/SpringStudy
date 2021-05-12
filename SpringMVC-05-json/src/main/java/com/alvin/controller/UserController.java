package com.alvin.controller;

import com.alvin.pojo.User;
import com.alvin.utils.JacksonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 1. Jackson
 * 2. @ResponseBody 和 @RestController
 * 3. 返回对象时候，其中 中文 乱码问题
 *      3.1 单一方法 @RequestMapping(value = "/u1/t3", produces = "application/json;charset=utf-8")
 *      3.2 统一解决 springmvc的配置文件中添加消息转换配置 <mvc:annotation-driven> 中增加信息转换配置
 * 4. 返回列表
 * 5. 返回日期
 *      5.1 SimpleDateFormat
 *      5.2 com.fasterxml.jackson.databind.ObjectMapper 配置
 *      5.3 JsonUtils工具类构建
 *
 */
@Controller
public class UserController {

    @ResponseBody   //不会经过视图解析器
    @RequestMapping("/u1/t1")
    public String t1()
    {
        User user = new User("Alvin炒饭",18,"男");
        return user.toString();
        // 前端显示的结果：User(name=Alvin??, age=18, sex=?)
    }

    @ResponseBody   //不会经过视图解析器
    @RequestMapping("/u1/t2")
    public String t2() throws JsonProcessingException {
        // jackson 的 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // 创建一个对象
        User user = new User("Alvin炒饭",18,"男");
        // 转换为字符串
        String str = mapper.writeValueAsString(user);
        return str;
/*
    前端显示的结果：{"name":"Alvin??","age":18,"sex":"?"}
    出现了乱码？？？
        过滤器解决的是：Client ---> Server;  请求时候出现的乱码问题!!!
        而这儿出现的是  Server ---> Client;  响应时候出现的乱码问题!!!

 */
    }

    /***
     * 解决响应时候的乱码问题：
     * 1. 单一方法 @RequestMapping(value = "/u1/t3", produces = "application/json;charset=utf-8")
     * 2. 统一解决 springmvc的配置文件中添加消息转换配置 <mvc:annotation-driven> 中增加信息转换配置
     *         <mvc:annotation-driven>
     *         <!-- 响应时期的 JSON 乱码问题解决 -->
     *         <mvc:message-converters>
     *             <bean class="org.springframework.http.converter.StringHttpMessageConverter">
     *                 <constructor-arg value="utf-8"/>
     *             </bean>
     *             <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
     *                 <property name="objectMapper">
     *                     <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
     *                         <property name="failOnEmptyBeans" value="false"/>
     *                     </bean>
     *                 </property>
     *             </bean>
     *         </mvc:message-converters>
     *     </mvc:annotation-driven>
     */

    @ResponseBody   //不会经过视图解析器
    @RequestMapping(value = "/u1/t3")
    public String t3() throws JsonProcessingException {
        // jackson 的 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // 创建一个对象
        User user = new User("Alvin炒饭",18,"男");
        // 转换为字符串
        String str = mapper.writeValueAsString(user);

        return str;
        /* 前端显示的结果：{"name":"Alvin炒饭","age":18,"sex":"男"}
        *  无乱码；
        * */
    }

    @ResponseBody   //不会经过视图解析器
    @RequestMapping(value = "/u1/t4")
    public String t4() throws JsonProcessingException {
        // jackson 的 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // 创建一个对象
        User user1 = new User("Alvin炒饭",18,"男");
        User user2 = new User("Alvin炒饭",18,"男");
        User user3 = new User("Alvin炒饭",18,"男");
        User user4 = new User("Alvin炒饭",18,"男");
        List<User> list =new ArrayList();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        // 转换为字符串
        String str = mapper.writeValueAsString(list);
        return str;
        /* 前端显示的结果：[{"name":"Alvin炒饭","age":18,"sex":"男"},{"name":"Alvin炒饭","age":18,"sex":"男"},{"name":"Alvin炒饭","age":18,"sex":"男"},{"name":"Alvin炒饭","age":18,"sex":"男"}]
         * */
    }

    @ResponseBody   //不会经过视图解析器
    @RequestMapping(value = "/u1/t5")
    public String t5() throws JsonProcessingException {
        // jackson 的 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // 创建一个对象
        Date date = new Date();
        // 转换为字符串，默认解析为时间戳
        String str = mapper.writeValueAsString(date);
        return str;
        /* 前端显示的结果(为一个时间戳)： 1620802922592  * */
    }

    @ResponseBody   //不会经过视图解析器
    @RequestMapping(value = "/u1/t6")
    public String t6() throws JsonProcessingException {
        // jackson 的 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // 创建一个对象
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 转换为字符串，默认解析为时间戳
        String str = mapper.writeValueAsString(sdf.format(date));
        return str;
        /* 前端显示的结果(为一个时间戳)： "2021-05-12 15:09:34"  * */
    }

    @ResponseBody   //不会经过视图解析器
    @RequestMapping(value = "/u1/t7")
    public String t7() throws JsonProcessingException {
        // jackson 的 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // mapper取消默认的时间戳显示
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        // 创建一个对象
        Date date = new Date();

        // 转换为字符串
        String str = mapper.writeValueAsString(date);
        return str;
        /* 前端显示的结果(为一个时间戳)： "2021-05-12 15:09:49"  * */
    }

    @ResponseBody   //不会经过视图解析器
    @RequestMapping(value = "/u1/t8")
    public String t8() throws JsonProcessingException {
        return JacksonUtils.getSjon(new Date());
        /* 前端显示的结果(为一个时间戳)： "2021-05-12 15:16:51"  * */
    }
}
