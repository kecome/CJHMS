package com.homework.exception;

import com.homework.response.ResponseMsg;
import com.homework.util.User;
import com.homework.util.UserUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 *
 * @author xuke
 * @create 2017-04-01 上午 9:32
 **/
@ControllerAdvice(basePackages = {"com.homework"})
public class GlobalExceptionHandler {
    @ExceptionHandler(value={MethodArgumentNotValidException.class, BusinessException.class, RuntimeException.class, Exception.class})
    @ResponseBody
    public ResponseMsg exceptionHandler(Exception e) {
        User user = UserUtil.getUser();
        //发生异常时清除当前会话信息
        UserUtil.removeUser();
        e.printStackTrace();
        ResponseMsg resp = new ResponseMsg();
        if(e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            BindingResult result = me.getBindingResult();
            String field = result.getFieldError().getField();
            String error = result.getFieldError().getDefaultMessage();
            resp.setCode("000011");
            resp.setMessage("[" + field + "]" + error);
            return resp;
        }
        if(e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            resp.setCode(businessException.getCode());
            resp.setMessage(businessException.getMsg());
            return resp;
        }
        if(e instanceof RuntimeException) {
            RuntimeException runtimeException = (RuntimeException) e;
            resp.setCode("0000");
            resp.setMessage(runtimeException.getMessage());
            return resp;
        }
        if(e instanceof Exception) {
            Exception exception = (Exception) e;
            resp.setCode("1111");
            resp.setMessage(exception.getMessage());
            return resp;
        }
        resp.setCode("xxxx");
        resp.setMessage("不知道出了什么问题");
        return resp;
    }
}
