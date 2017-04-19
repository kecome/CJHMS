package com.homework.exception;

/**
 * Created by xuke on 2017/3/31 0031.
 */
public enum ErrorInfo {

    /**------------------------作业------------------------**/
    ID_IS_NULL("00001","id不能为空", "\\u5bf9\\u8c61\\u4e3a\\u7a7a"),
    USER_IS_NULL("703","您尚未登陆，或者Session超时", "\\u60a8\\u5c1a\\u672a\\u767b\\u9646\\uff0c\\u6216\\u8005\\u0053\\u0065\\u0073\\u0073\\u0069\\u006f\\u006e\\u8d85\\u65f6"),
    HTTP_CONNECTION_NULL("0002", "http请求返回null", ""),
    ClASSID_IS_NULL("0003", "班级id为空", ""),
    YUN_REP_ERROR("0005", "云基础平台数据返回异常", ""),
    HOMEWORK_PUBLIC("0004", "作业已发布", "");

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
