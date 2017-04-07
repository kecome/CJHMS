package com.homework.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json和对象转换工具类
 *
 * @author xuke
 * @create 2017-04-01 下午 16:17
 **/
public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String beanToJson(Object obj) throws Exception {
        return mapper.writeValueAsString(obj);
    }

    public static Object jsonToBean(String json, Class clazz) throws Exception {
        return mapper.readValue(json, clazz);
    }
}
