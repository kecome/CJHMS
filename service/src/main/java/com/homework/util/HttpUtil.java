package com.homework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * http请求工具类
 *
 * @author xuke
 * @create 2017-04-06 下午 18:50
 **/
public class HttpUtil {

    public static String GET = "get";

    public static String POST = "post";

    private static String serverTokenKey = "homeWork";

    private static String serverTokenVal = "FD777E29B73DC4B60DE98F1";

    /**
     * 封装get和post请求 参数类型&key=value
     * @param url
     * @param param
     * @param method
     * @return
     */
    public static String send(String url, TreeMap<String, Object> param,String method) {
        if(method == null || method.equals("")) {
            return null;
        }
        TreeMap<String, Object> header = new TreeMap<>();
       // header.put("serverTokenKey", serverTokenKey);
       // header.put("serverTokenVal", serverTokenVal);
        if(param.containsKey("token")) {
            header.put("token", param.get("token"));
            param.remove("token");
        }
        if(UserUtil.getUser() != null) {
            header.put("token", UserUtil.getUser().getToken());
        }
        StringBuilder builder = new StringBuilder("");
        if(param!= null && param.size() > 0) {
            for(Map.Entry entry : param.entrySet()) {
                builder.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            builder.deleteCharAt(builder.length()-1);
        }
        String result = "";
        if(method.equals("get")) {
            result = sendGet(url, builder.toString(),header);
        }else if(method.equals("post")) {
            result = sendPost(url, builder.toString(), header);
        }
        return result;
    }

    public static String postJson(String url,String json) {
        TreeMap<String, Object> header = new TreeMap<>();
       // header.put("serverTokenKey", serverTokenKey);
       // header.put("serverTokenVal", serverTokenVal);
        if(UserUtil.getUser() != null) {
            header.put("token", UserUtil.getUser().getToken());
        }
        // 设置文件类型:
        header.put("contentType", "application/json");
        return sendPost(url, json, header);
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    private static String sendGet(String url, String param, TreeMap<String, Object> header) {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url;
        if(!param.equals("")) {
            urlNameString = url + "?" + param;
        }
        try {
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //设置http头信息
            if(header != null && header.size() > 0) {
                for(Map.Entry<String, Object> entry:header.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), (String) entry.getValue());
                }
            }
            // 建立实际的连接
            connection.connect();
            //设置连接超时间
            connection.setReadTimeout(5000);
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            UserUtil.removeUser();
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private static String sendPost(String url, String param, TreeMap<String, Object> header) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //设置http头信息
            if(header != null && header.size() > 0) {
                for(Map.Entry<String, Object> entry:header.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), (String) entry.getValue());
                }
            }
            //conn.setRequestProperty("serverTokenKey", serverTokenKey);
            //设置连接超时间
            conn.setReadTimeout(5000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            UserUtil.removeUser();
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
