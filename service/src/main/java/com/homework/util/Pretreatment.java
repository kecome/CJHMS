package com.homework.util;

import java.util.Date;
import java.util.Map;

/**
 * 预处理
 *
 * @author xuke
 * @create 2017-04-06 下午 15:34
 **/
public class Pretreatment {

    public static Date now() {
        return new Date();
    }

    public static long nextId() {
        SnowflakeIdGenerator sd = SnowflakeIdGenerator.getInstance();
        return sd.nextId();
        //return new SnowflakeIdWorker(0L, 0L).nextId();
    }

//    public static void main(String[] args) {
//        for(int i=0;i<100;i++) {
//            System.out.println(Pretreatment.nextId());
//        }
//    }

    public static String getOrder(Map<String, String> order) {
        if(order != null && order.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for(Map.Entry entry : order.entrySet()) {
                buffer.append(entry.getKey() + " " + entry.getValue() + ",");
            }
            return buffer.deleteCharAt(buffer.length() -1).toString();
        }
        return "";
    }
}
