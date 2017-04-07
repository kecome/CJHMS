package com.homework.data;

/**
 * 题目查询参数类
 *
 * @author xuke
 * @create 2017-04-07 上午 10:52
 **/
public class QuestionParam extends BaseParam {
    /**
     * 作业id
     */
    private Long homeworkId;

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }
}
