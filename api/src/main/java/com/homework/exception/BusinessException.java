package com.homework.exception;

/**
 * 作业异常封装
 *
 * @author xuke
 * @create 2017-03-31 下午 14:45
 **/
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    /**
     * 异常码
     */
    private String code;
    /**
     * 异常提示信息
     */
    private String msg;
    /**
     * 方法参数
     */
    private String param;

    public BusinessException(){
        super();
    }

    public BusinessException(String code, String msg){
        super();
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String code, String msg, String param){
        super();
        this.code = code;
        this.msg = msg;
        this.param = param;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

}
