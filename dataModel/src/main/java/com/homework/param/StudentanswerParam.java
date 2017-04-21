package com.homework.param;

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
    private Long homeworkId;
    /**
     * 学生id
     */
    private Long studentId;

    private Boolean show = true;

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
}
