package com.homework.model;

/**
 * 作业班级关联表
 *
 * @author xuke
 * @create 2017-04-06 下午 18:09
 **/
public class HomeworkClass extends BaseModel {
    /**
     *作业id
     */
    private Long homeworkId;
    /**
     * 所属班级id
     */
    private Long classId;
    /**
     * 班级名称
     */
    private String className;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
