package com.homework.response;/**
 * Created by Administrator on 2017/3/31 0031.
 */

/**
 * 请求返回内容
 *
 * @author
 * @create 2017-03-31 下午 15:19
 **/
public class ResponseMsg<T> {
    /**
     * 请求响应码
     */
    private String code = "0";
    /**
     * 响应码描述
     */
    private String message = "成功";
    /**
     * 响应数据
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
