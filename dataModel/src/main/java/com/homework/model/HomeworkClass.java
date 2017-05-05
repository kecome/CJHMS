package com.homework.model;

import io.swagger.annotations.ApiModelProperty;

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
	@ApiModelProperty("作业id")
    private Long homeworkId;
    /**
     * 所属班级id
     */
	@ApiModelProperty("所属班级id")
    private Long classId;
    /**
     * 班级名称
     */
	@ApiModelProperty("班级名称")
    private String className;
    /**
     * 作业提交人数
     */
	
	@ApiModelProperty("作业提交人数")
    private Integer submitCount;
    /**
     * 已批阅人数
     */
	@ApiModelProperty("已批阅人数")
    private Integer markCount;
    /**
     * 作业发布人数
     */
	@ApiModelProperty("作业发布人数")
    private Integer count;

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

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public Integer getMarkCount() {
        return markCount;
    }

    public void setMarkCount(Integer markCount) {
        this.markCount = markCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
