package com.homework.data;

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
    private List<Long> studentIds;
    /**
     * 作业id
     */
    private Long homeworkId;
    /**
     * 老师id
     */
    private Long teacherId;
    /**
     * 作业题目
     */
    private List<Question> questions;
    /**
     * 学生答案
     */
    private List<Studentanswer> answers;

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

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
}
