package com.homework.model;

/**
 * 消息类
 *
 * @author xuke
 * @create 2017-04-07 上午 9:18
 **/
public class message extends BaseModel {
    /**
     * 消息接收者id号，学生
     */
    private Long studentId;
    /**
     * 消息发送者id号，老师
     */
    private Long teacherId;
    /**
     * 消息类型：【0：作业批阅消息】
     */
    private Integer type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否查看：【0：未查看；1：已查看】
     */
    private Integer isRead;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
