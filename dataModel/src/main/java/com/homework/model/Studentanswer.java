package com.homework.model;

import io.swagger.annotations.ApiModelProperty;

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
	@ApiModelProperty("所属题目id号")
    private Long questionId;
    /**
     * 学生id号
     */
	@ApiModelProperty("学生id号")
    private Long studentId;
    /**
     * 题目答案,主观题为照片url
     */
	@ApiModelProperty("题目答案,主观题为照片url")
    private String answer;
    /**
     * 正确与否：【0：正确；1：错误】
     */
	@ApiModelProperty("正确与否：【0：正确；1：错误】")
    private Integer isRight;
    /**
     * 批阅内容
     */
	@ApiModelProperty("批阅内容")
    private String comment;
    /**
     * 作答耗时
     * 不需要入库，通过作答日志计算得到
     */
	@ApiModelProperty("作答耗时")
    private Integer time;

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
