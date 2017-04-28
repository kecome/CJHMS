package com.homework.constant;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public enum QuestionType {
    SINGLE(0, "单选题"),MULTIPLE(1, "多选题"),SUBJECTIVE(2, "主观题");

    public int value;
    public String des;

    private QuestionType(int value, String des) {
        this.value = value;
        this.des = des;
    }
}
