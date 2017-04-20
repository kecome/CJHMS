package com.homework.model;

/**
 * 学生答题实体类
 *
 * @author xuke
 * @create 2017-04-07 上午 9:10
 **/
public class Studentanswer extends BaseModel {
    /**
     *所属题目id号
     */
    private Long questionId;
    /**
     * 学生id号
     */
    private Long studentId;
    /**
     * 题目答案,主观题为照片url
     */
    private String answer;
    /**
     * 正确与否：【0：正确；1：错误】
     */
    private Integer isRight;
    /**
     * 老师批阅
     */
    private String comment;
    /**
     * 正在做的题 0：不是；1：是
     */
    private int active;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getIsRight() {
        return isRight;
    }

    public void setIsRight(Integer isRight) {
        this.isRight = isRight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
