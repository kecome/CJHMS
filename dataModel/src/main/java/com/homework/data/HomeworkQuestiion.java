package com.homework.data;

import com.homework.model.Homework;
import com.homework.model.Question;

import java.util.List;

/**
 * 作业，题目数据模型
 *
 * @author xuke
 * @create 2017-04-01 下午 18:46
 **/
public class HomeworkQuestiion {
    /**
     * 作业信息
     */
    private Homework homework;
    /**
     * 班级学生id
     */
    private List<Long> classIds;
    /**
     * 题目信息
     */
    private List<Question> questions;

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Long> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<Long> classIds) {
        this.classIds = classIds;
    }
}
