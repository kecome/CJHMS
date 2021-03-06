package com.homework.util;

import org.slf4j.MDC;

/**
 * 会话工具类
 *
 * @author xuke
 * @create 2017-04-11 上午 11:14
 **/
public final class UserUtil {
    public static final String TEACHER = "TEACHER";
    public static final String STUDENT = "STUDENT";

    private static final ThreadLocal<User> userLocal = new ThreadLocal<>();

    private UserUtil(){}

    public static final void putUser(User user) {
        userLocal.set(user);
        MDC.put("id", user.getId() + "");
        MDC.put("name", user.getUsername() + "");
        MDC.put("role", user.getRole() + "");
    }

    public static final User getUser() {
        User user = userLocal.get();
        return user;
//        if(user == null) {
//            throw new BusinessException(ErrorInfo.USER_IS_NULL.code, ErrorInfo.USER_IS_NULL.desc);
//        } else {
//            return user;
//        }
    }

    public static final void removeUser() {
        userLocal.remove();
        MDC.clear();
    }
}
