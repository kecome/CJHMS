package com.homework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

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
    @ApiModelProperty(value = "作业ID，教师布置作业时生成", required = true)
    private Long homeworkId;
    /**
     * 学生id号
     */
    @ApiModelProperty("学生id号")
    private Long studentId;
    /**
     * 学生名称
     */
    @ApiModelProperty("学生名称")
    private String studentName;
    /**
     * 班级id号
     */
    @ApiModelProperty("班级id号")
    private Long classId;
    /**
     * 学生作业计时
     */
    @ApiModelProperty("学生作业计时")
    private int time;
    /**
     * 学生是否提交：【0：未提交；1：已提交】
     */
    @ApiModelProperty("学生是否提交：【0：未提交；1：已提交】")
    private Integer submit;
    /**
     * 老师是否批阅：【0：未批阅；1：已批阅】
     */
    @ApiModelProperty("老师是否批阅：【0：未批阅；1：已批阅】")
    private Integer mark;
    /**
     * 作业提交时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date submitTime;

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

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}
