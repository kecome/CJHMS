package com.homework.data;

import com.homework.model.Homework;
import com.homework.model.Question;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * 作业，题目数据模型
 *
 * @author xuke
 * @create 2017-04-01 下午 18:46
 **/
public class HomeworkQuestiion {
    /**
     * 作业信息
     */
	@ApiModelProperty("作业信息")
    private Homework homework;
    /**
     * 班级id,name信息
     */
	@ApiModelProperty("班级id,name信息")
    private List<Map<String,String>> classes;
    /**
     * 题目信息
     */
	@ApiModelProperty("题目信息")
    private List<Question> questions;

	public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

	public List<Map<String, String>> getClasses() {
		return classes;
	}

	public void setClasses(List<Map<String, String>> classes) {
		this.classes = classes;
	}

   
}
