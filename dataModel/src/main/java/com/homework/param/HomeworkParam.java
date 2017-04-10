package com.homework.param;

/**
 * 作业查询参数类
 *
 * @author xuke
 * @create 2017-04-05 下午 19:00
 **/
public class HomeworkParam extends BaseParam {
    /**
     * 老师id
     */
    private Long teacherId;
    /**
     * 标题
     */
    private String title;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
