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

    private Boolean show = true;

    /**
     * 题目类型
     */
    private Integer type;

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
}
