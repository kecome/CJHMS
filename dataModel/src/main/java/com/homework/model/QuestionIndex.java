package com.homework.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xuke
 * @create 2017-04-21 下午 20:49
 **/
public class QuestionIndex extends BaseModel {
    /**
     * 题目id
     */
	@ApiModelProperty("题目id")
    private Long questionId;
    /**
     * 学生作业id
     */
	@ApiModelProperty("学生作业id")
    private Long studentworkId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getStudentworkId() {
        return studentworkId;
    }

    public void setStudentworkId(Long studentworkId) {
        this.studentworkId = studentworkId;
    }
}
