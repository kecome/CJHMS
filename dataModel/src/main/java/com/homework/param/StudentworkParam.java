package com.homework.param;

/**
 * @author xuke
 * @create 2017-04-07 下午 16:55
 **/
public class StudentworkParam extends BaseParam{
    /**
     * 作业id
     */
    private Long homeworkId;
    /**
     * 学生id
     */
    private Long studentId;
    /**
     * 班级id
     */
    private Long classId;
    /**
     * 老师id
     */
    private Long teacherId;
    /**
     * 0：未提交；1：已提交
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
