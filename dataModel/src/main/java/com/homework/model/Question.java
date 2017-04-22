package com.homework.model;

/**
 * 题目实体类
 *
 * @author xuke
 * @create 2017-04-07 上午 8:59
 **/
public class Question extends BaseModel {
    /**
     * 作业id
     */
    private Long homeworkId;
    /**
     * 题型：【0：选择题；1：主观题】
     */
    private Integer type;
    /**
     * 题干
     */
    private String title;
    /**
     * 题目序号，作业展示时用于排序
     */
    private Integer seq;
    /**
     * 题目答案
     */
    private String answer;

    /**
     * 答案选项
     */
    private String item;

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
