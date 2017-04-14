package com.homework.param;

/**
 * @author xuke
 * @create 2017-04-14 下午 16:56
 **/
public class MessageParam extends BaseParam {
    /**
     * 学生id
     */
    private Long studentId;
    /**
     * 老师id
     */
    private Long teacherId;
    /**
     * 消息类型
     */
    private Integer type;
    /**
     * 是否已读
     */
    private Integer isReader;

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

    public Integer getIsReader() {
        return isReader;
    }

    public void setIsReader(Integer isReader) {
        this.isReader = isReader;
    }
}
