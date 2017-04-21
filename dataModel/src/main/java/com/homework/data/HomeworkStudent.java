package com.homework.data;

import com.homework.model.Homework;
import com.homework.model.Question;
import com.homework.model.Studentanswer;

import java.util.List;

/**
 * @author xuke
 * @create 2017-04-19 下午 13:50
 **/
public class HomeworkStudent {
    /**
     * 学生id集合
     */
   // private List<Long> studentIds;
    /**
     * 作业
     */
    private Homework homework;
    /**
     * 老师id
     */
    private Long teacherId;
    /**
     * 老师是否批阅：【0：未批阅；1：已批阅】
     */
    private Integer mark;
    /**
     * 是否显示学生作业答案
     */
    private boolean show = false;

    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 学生名字
     */
    private String studentName;

    /**
     * 作业题目
     */
    private List<Question> questions;
    /**
     * 学生答案
     */
    private List<Studentanswer> answers;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Studentanswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Studentanswer> answers) {
        this.answers = answers;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
