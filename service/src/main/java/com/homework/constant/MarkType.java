package com.homework.constant;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public enum MarkType {
    NO(0,"未批阅"), YES(1,"已批阅");

    public int value;
    public String des;

    private MarkType(int value, String des) {
        this.value = value;
        this.des = des;
    }
}
