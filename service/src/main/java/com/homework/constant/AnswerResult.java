package com.homework.constant;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public enum AnswerResult {
    RIGHT(0,"正确"), ERROR(1,"错误");

    public int value;
    public String des;

    private AnswerResult(int value, String des) {
        this.value = value;
        this.des = des;
    }
}
