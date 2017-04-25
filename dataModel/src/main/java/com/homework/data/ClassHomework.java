package com.homework.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homework.model.HomeworkClass;

import java.util.Date;
import java.util.List;

/**
 * 班级和作业
 *
 * @author xuke
 * @create 2017-04-19 上午 10:21
 **/
public class ClassHomework {
    /**
     *作业名称
     */
    private String title;
    /**
     * 班级id
     */
    private Long classId;
    /**
     * 作业id
     */
    private Long homeworkId;
    /**
     * 作业状态：【0：未发布；1：已发布】
     */
    private Integer status;
    /**
     * 预约发布时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publicTime;
    /**
     * 截止提交时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;
    /**
     * 设定班级
     */
    private List<HomeworkClass> classes;
    /**
     * 老师id
     */
    private Long teacherId;
    /**
     * 作业提交人数
     */
    private Integer submitCount;
    /**
     * 已批阅人数
     */
    private Integer markCount;
    /**
     * 作业发布人数
     */
    private Integer count;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date created;
    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updated;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<HomeworkClass> getClasses() {
        return classes;
    }

    public void setClasses(List<HomeworkClass> classes) {
        this.classes = classes;
    }

    public Integer getMarkCount() {
        return markCount;
    }

    public void setMarkCount(Integer markCount) {
        this.markCount = markCount;
    }
}
