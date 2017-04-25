package com.homework.mapper;

import com.homework.data.ClassHomework;
import com.homework.model.HomeworkClass;
import com.homework.param.HomeworkClassParam;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7 0007.
 */
public interface HomeworkClassMapper {
    Long insertHomeworkClass(HomeworkClass homeworkClass);
    int deleteHomeworkClass(Long homeworkId);
    List<HomeworkClass> selectClassList(HomeworkClassParam param);
    Integer count(HomeworkClassParam param);
    List<HomeworkClass> selectList(long id);
    List<Long> selectClassId(long id);
    List<ClassHomework> selectClassHomework(HomeworkClassParam param);
    Integer countClassHomework(HomeworkClassParam param);
}
