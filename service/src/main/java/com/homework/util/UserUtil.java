package com.homework.util;

/**
 * 会话工具类
 *
 * @author xuke
 * @create 2017-04-11 上午 11:14
 **/
public final class UserUtil {
    public static final String TEACHER = "teacher";
    public static final String STUDENT = "student";

    private static final ThreadLocal<User> userLocal = new ThreadLocal();

    private UserUtil(){}

    public static final void putUser(User user) {
        userLocal.set(user);
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
    }
}
