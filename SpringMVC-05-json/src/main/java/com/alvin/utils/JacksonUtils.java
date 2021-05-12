package com.alvin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JacksonUtils {

    public static String getSjon(Object obj)  {
        return getSjon(obj,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getSjon(Object obj,String format)  {
        // jackson 的 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // mapper 取消默认的时间戳显示
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        // 自定义时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        try{
            // 转换为字符串并返回
            return mapper.writeValueAsString(obj);
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
