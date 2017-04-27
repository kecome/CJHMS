package com.homework.data;

import com.homework.model.Homework;
import com.homework.model.HomeworkClass;

import java.util.List;

/**
 * 作业班级
 *
 * @author xuke
 * @create 2017-04-19 上午 10:21
 **/
public class ClassHomework extends Homework{
    /**
     * 设定班级
     */
    private List<HomeworkClass> classes;

    public List<HomeworkClass> getClasses() {
        return classes;
    }

    public void setClasses(List<HomeworkClass> classes) {
        this.classes = classes;
    }
}
