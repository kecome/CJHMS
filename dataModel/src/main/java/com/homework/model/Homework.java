package com.homework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 作业实体类与数据库映射
 *
 * @author
 * @create 2017-03-31 上午 10:48
 **/
@Validated
public class Homework extends BaseModel {
    /**
     * 教师id号
     */
	@ApiModelProperty(value="教师id号",required=true)
    @NotNull(message = "老师id不能为空")
    private Long teacherId;
    /**
     * 所属科目id号
     */
	@ApiModelProperty(value="所属科目id号",required=true)
    @NotNull(message = "所属科目id不能为空")
    private Long subjectId;
    /**
     * 作业名称
     */
	@ApiModelProperty(value="作业名称",required=true)
    @NotNull(message = "作业名称不能为空")
    private String title;
    /**
     * 科目名称
     */
	@ApiModelProperty(value="科目名称",required=true)
    @NotNull(message = "课目名称不能为空")
    private String subjectName;
    /**
     * 相关知识点
     */
	@ApiModelProperty("相关知识点")
    private String knowledge;
    /**
     * 预估完成时间,单位/分钟
     */
	@ApiModelProperty("预估完成时间,单位/分钟")
    private Integer spentTime;
    /**
     * 作业状态：【0：未发布；1：已发布】
     */
	@ApiModelProperty(value="作业状态：【0：未发布；1：已发布】",required=true)
    @NotNull(message = "状态不能为空")
    private Integer status;
    /**
     * 预约发布时间
     */
	@ApiModelProperty("预约发布时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    private Date publicTime;
    /**
     * 截止提交时间
     */
	@ApiModelProperty("截止提交时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso= DateTimeFormat.ISO.DATE_TIME)
    private Date endTime;

    /**
     * 是否批阅
     */
	@ApiModelProperty("是否批阅")
    private Integer mark;

    /**
     * 作业提交时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date submitTime;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public Integer getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Integer spentTime) {
        this.spentTime = spentTime;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}
