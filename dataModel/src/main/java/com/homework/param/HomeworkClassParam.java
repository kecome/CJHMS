package com.homework.param;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author xuke
 * @create 2017-04-17 下午 19:25
 **/
public class HomeworkClassParam extends BaseParam{
    /**
     * 作业id
     */
    private Long homeworkId;
    /**
     * 班级id
     */
    private Long classId;
    /**
     * 老师id
     */
    private Long teacherId;
    /**
     * 作业发布时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publicTime;

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }
}
