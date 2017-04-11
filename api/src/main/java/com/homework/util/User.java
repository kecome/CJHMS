package com.homework.util;

/**
 * 当前会话的用户信息
 *
 * @author xuke
 * @create 2017-04-11 下午 14:39
 **/
public class User {
    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
