package com.homework.exception;

import com.homework.response.ResponseMsg;
import com.homework.util.User;
import com.homework.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(value={MethodArgumentNotValidException.class, BusinessException.class, RuntimeException.class, Exception.class})
    @ResponseBody
    public ResponseMsg exceptionHandler(Exception e) {
        User user = UserUtil.getUser();
        //发生异常时清除当前会话信息
        UserUtil.removeUser();
        ResponseMsg resp = new ResponseMsg();
        if(e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            BindingResult result = me.getBindingResult();
            String field = result.getFieldError().getField();
            String error = result.getFieldError().getDefaultMessage();
            resp.setCode(ErrorInfo.ARGUMENT_NOT_VALID.code);
            resp.setMessage("[" + field + "]" + error);
            logger.error("参数较验异常["+ErrorInfo.ARGUMENT_NOT_VALID.code+"]:" + "[" + field + "]" + error, e);
            return resp;
        }
        if(e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            resp.setCode(businessException.getCode());
            resp.setMessage(businessException.getMsg());
            logger.error("自定义异常["+businessException.getCode()+"]:" + businessException.getMsg(), e);
            return resp;
        }
        if(e instanceof RuntimeException) {
            RuntimeException runtimeException = (RuntimeException) e;
            resp.setCode(ErrorInfo.RUNTIME_EXCEPTIOON.code);
            resp.setMessage(ErrorInfo.RUNTIME_EXCEPTIOON.desc);
            logger.error("[" + ErrorInfo.RUNTIME_EXCEPTIOON.code + "]" + ErrorInfo.RUNTIME_EXCEPTIOON.desc + ":", e);
            return resp;
        }
        if(e instanceof Exception) {
            Exception exception = (Exception) e;
            resp.setCode(ErrorInfo.RUNTIME_EXCEPTIOON.code);
            resp.setMessage(ErrorInfo.RUNTIME_EXCEPTIOON.desc);
            logger.error("[" + ErrorInfo.RUNTIME_EXCEPTIOON.code + "]" + ErrorInfo.RUNTIME_EXCEPTIOON.desc + ":", e);
            return resp;
        }
        resp.setCode(ErrorInfo.UNKNOW_EXCEPTION.code);
        resp.setMessage(ErrorInfo.UNKNOW_EXCEPTION.desc);
        logger.error("[" + ErrorInfo.RUNTIME_EXCEPTIOON.code + "]" + ErrorInfo.RUNTIME_EXCEPTIOON.desc + ":", e);
        return resp;
    }
}
