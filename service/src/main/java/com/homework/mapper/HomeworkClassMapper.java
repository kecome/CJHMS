package com.homework.mapper;

import com.homework.model.HomeworkClass;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7 0007.
 */
public interface HomeworkClassMapper {
    Long insertHomeworkClass(HomeworkClass homeworkClass);
    int deleteHomeworkClass(Long homeworkId);
    List<HomeworkClass> selectList(Long homeworkId);
}
