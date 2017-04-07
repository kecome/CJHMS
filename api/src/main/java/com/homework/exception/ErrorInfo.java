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
