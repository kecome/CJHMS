package com.homework.param;

/**
 * @author xuke
 * @create 2017-04-22 上午 11:25
 **/
public class QuestionIndexParam {
    private Long studentworkId;
    private int time;
    private Long questionId;
    private Long studentId;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    public Long getStudentworkId() {
        return studentworkId;
    }

    public void setStudentworkId(Long studentworkId) {
        this.studentworkId = studentworkId;
    }
}
