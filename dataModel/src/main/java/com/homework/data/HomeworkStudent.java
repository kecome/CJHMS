package com.homework.data;

import com.homework.model.*;

import io.swagger.annotations.ApiModelProperty;

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
	@ApiModelProperty("作业")
    private Homework homework;
    /**
     * 作业题目
     */
	@ApiModelProperty("作业题目")
    private List<Question> questions;
    /**
     * 是否显示学生作业答案
     */
	@ApiModelProperty("是否显示学生作业答案")
    private boolean show = false;

    private Studentwork studentwork;

    /**
     * 学生答案
     */
    private List<Studentanswer> answers;
    /**
     * 定位当前答题
     */
    private QuestionIndex questionIndex;

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

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Studentwork getStudentwork() {
        return studentwork;
    }

    public void setStudentwork(Studentwork studentwork) {
        this.studentwork = studentwork;
    }

    public QuestionIndex getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(QuestionIndex questionIndex) {
        this.questionIndex = questionIndex;
    }
}
