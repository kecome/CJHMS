package com.homework.exception;

/**
 * Created by xuke on 2017/3/31 0031.
 */
public enum ErrorInfo {

    /**------------------------作业------------------------**/
    /**
     * id为空
     */
    ID_IS_NULL("00001","id不能为空", "\\u5bf9\\u8c61\\u4e3a\\u7a7a"),
    USER_IS_NULL("703","您尚未登陆，或者Session超时", "\\u60a8\\u5c1a\\u672a\\u767b\\u9646\\uff0c\\u6216\\u8005\\u0053\\u0065\\u0073\\u0073\\u0069\\u006f\\u006e\\u8d85\\u65f6"),
    HTTP_CONNECTION_NULL("704", "http请求返回null", ""),
    ERROR_ADD_USER("LUO002","添加用户失败", ""),
    UNKNOWN_ERROR("LUO999","系统繁忙，请稍后再试....", "");

    /**------------------------xxx------------------------**/


    /**
     * 异常码
     */
    public String code;
    /**
     * 异常描述
     */
    public String desc;
    /**
     * 异常描述  unicode编码
     */
    public String unicode;
    private ErrorInfo(String code, String desc, String unicode) {
        this.code = code;
        this.desc = desc;
        this.unicode = unicode;
    }
}
