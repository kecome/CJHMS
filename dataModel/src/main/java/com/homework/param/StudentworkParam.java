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
     * 0：未提交；1：已提交
     */
    private int submit;

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

    public int getSubmit() {
        return submit;
    }

    public void setSubmit(int submit) {
        this.submit = submit;
    }
}
