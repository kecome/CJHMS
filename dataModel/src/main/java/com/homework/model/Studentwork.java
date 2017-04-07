package com.homework.model;

/**
 * 学生作业实体类
 *
 * @author xuke
 * @create 2017-04-07 上午 9:06
 **/
public class Studentwork extends BaseModel {
    /**
     * 作业id
     */
    private Long homeworkId;
    /**
     * 学生id号
     */
    private Long studentId;
    /**
     * 学生是否提交：【0：未提交；1：已提交】
     */
    private Integer submit;
    /**
     * 老师是否批阅：【0：未批阅；1：已批阅】
     */
    private Integer mark;

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

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
