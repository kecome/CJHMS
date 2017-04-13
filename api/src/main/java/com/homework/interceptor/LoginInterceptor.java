package com.homework.interceptor;

import com.homework.annotation.LoginIgnore;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.response.ResponseMsg;
import com.homework.util.HttpUtil;
import com.homework.util.User;
import com.homework.util.UserUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.TreeMap;

/**
 * 登录验证拦截器
 *
 * @author xuke
 * @create 2017-03-31 上午 11:04
 **/
public class LoginInterceptor implements HandlerInterceptor{

    @Value("${cbp_host}")
    private String host;

    @Autowired
    private Environment env;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResponseMsg msg = new ResponseMsg();
        System.out.println(">>>LoginInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class clazz = handlerMethod.getBeanType();
        //判断请求处理类是否加了LoginIgnore注解
        Annotation clazzAnnotation = clazz.getAnnotation(LoginIgnore.class);
       // if(annotation != null) return true;   // 只有返回true才会继续向下执行，返回false取消当前请求
        //判断请求方法是否加了LoginIgnore注解
        Annotation methodAnnotation = handlerMethod.getMethod().getAnnotation(LoginIgnore.class);
        //if(annotation != null) return true;
        if(clazzAnnotation == null && methodAnnotation == null) {  //没加忽略登录拦截注解，进入token验证
            TreeMap<String, Object> param = new TreeMap<>();
            param.put("token", request.getHeader("token"));
            //http://10.0.3.188:8090/
            //String json = HttpUtil.send("http://10.0.0.214:8080/cbp/unverify/getTokeneffective.cbp",null, header, HttpUtil.POST);
            String json = HttpUtil.send("http://10.0.3.188:8090/unverify/getTokeneffective.cbp",param, HttpUtil.POST);
            if(StringUtils.isEmpty(json)) {
                throw new BusinessException(ErrorInfo.HTTP_CONNECTION_NULL.code, ErrorInfo.HTTP_CONNECTION_NULL.desc);
            }
            JSONObject obj = JSONObject.fromObject(json);
            int code = obj.getInt("code");
            if(code != 0) {
                throw new BusinessException(ErrorInfo.USER_IS_NULL.code, ErrorInfo.USER_IS_NULL.desc);
            }
            JSONObject data = obj.getJSONObject("data");
            User user = new User();
            user.setId(data.getLong("id"));
            user.setUsername(data.getString("userName"));
            user.setToken(request.getHeader("token"));
            UserUtil.putUser(user);
            return true;
        }else {  //忽略登录拦截
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //清除当前请求会话信息
        UserUtil.removeUser();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
