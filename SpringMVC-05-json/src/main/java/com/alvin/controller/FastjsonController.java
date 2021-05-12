package com.alvin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alvin.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FastjsonController {
    @ResponseBody   //不会经过视图解析器
    @RequestMapping(value = "/f1/t1")
    public String t1()  {
        // 创建对象
        User user1 = new User("Alvin炒饭",18,"男");
        User user2 = new User("Alvin炒饭",18,"男");
        List<User> list =new ArrayList();
        list.add(user1);
        list.add(user2);
        // 转换为字符串
        System.out.println(" ============== java 转 String ============== ");
        String str = JSON.toJSONString(list);
        System.out.println(" JSON.toJSONString(list) : " + str);
        String str1 = JSON.toJSONString(user1);
        System.out.println(" JSON.toJSONString(user1) : "+ str1);


        System.out.println(" ============== String 转 java ============== ");
        User user = JSON.parseObject(str1, User.class);
        System.out.println(" JSON.parseObject(str1, User.class) : "+ user);


        System.out.println(" ============== java 转 JSON对象 ============== ");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
        System.out.println(" JSON.parseObject(str1, User.class) : "+ jsonObject1.getString("name"));

        System.out.println(" ============== JSON对象 转 java ============== ");
        User to_java_user = JSON.toJavaObject(jsonObject1, User.class);
        System.out.println(" JSON.toJavaObject(jsonObject1, User.class) : "+ to_java_user);
        return str;
/**
 * ============== java 转 String ==============
 *  JSON.toJSONString(list) : [{"age":18,"name":"Alvin炒饭","sex":"男"},{"age":18,"name":"Alvin炒饭","sex":"男"}]
 *  JSON.toJSONString(user1) : {"age":18,"name":"Alvin炒饭","sex":"男"}
 *  ============== String 转 java ==============
 *  JSON.parseObject(str1, User.class) : User(name=Alvin炒饭, age=18, sex=男)
 *  ============== java 转 JSON对象 ==============
 *  JSON.parseObject(str1, User.class) : Alvin炒饭
 *  ============== JSON对象 转 java ==============
 *  JSON.toJavaObject(jsonObject1, User.class) : User(name=Alvin炒饭, age=18, sex=男)
 *
 *  前端显示：
 *      [{"age":18,"name":"Alvin炒饭","sex":"男"},{"age":18,"name":"Alvin炒饭","sex":"男"}]
 *
 **/
    }
}
