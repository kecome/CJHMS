package com.homework.data;

/**
 * 用户token信息
 *
 * @author xuke
 * @create 2017-04-11 下午 13:52
 **/
public class TokenInfo {
    /**
     * token字符串
     */
    private String token;
    /**
     * token是否有效
     */
    private Boolean active;
    /**
     * token有效时间
     */
    private Long activeTime;

    public TokenInfo(){}

    public TokenInfo(String Token, Boolean active, Long activeTime) {
        this.token = Token;
        this.active = active;
        this.activeTime = activeTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
