package com.homework.param;

import javax.validation.constraints.NotNull;

/**
 * 学生答题查询
 *
 * @author xuke
 * @create 2017-04-10 下午 17:05
 **/
public class StudentanswerParam extends BaseParam{
    /**
     * 作业id
     */
    @NotNull(message = "作业id不能为空")
    private Long homeworkId;
    /**
     * 学生id
     */
    private Long studentId;
    /**
     *是否显示学生答案
     */
    private Boolean show = true;
    /**
     * 题目类型
     */
    private Integer type;
    /**
     * 是否显示所有题目  0：是 1：只显示学生作答的题目
     */
    private int question;

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }
}
