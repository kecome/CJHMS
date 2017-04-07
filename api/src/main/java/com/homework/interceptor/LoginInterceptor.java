package com.homework.interceptor;

import com.homework.annotation.LoginIgnore;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * 登录验证拦截器
 *
 * @author xuke
 * @create 2017-03-31 上午 11:04
 **/
public class LoginInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(">>>LoginInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class clazz = handlerMethod.getBeanType();
        //判断请求处理类是否加了LoginIgnore注解
        Annotation annotation = clazz.getAnnotation(LoginIgnore.class);
        if(annotation != null) return true;
        //判断请求方法是否加了LoginIgnore注解
        annotation = handlerMethod.getMethod().getAnnotation(LoginIgnore.class);
        if(annotation != null) return true;
        //较验token


        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
