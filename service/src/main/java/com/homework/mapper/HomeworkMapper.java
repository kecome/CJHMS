package com.homework.mapper;

import com.homework.data.HomeworkParam;
import com.homework.model.Homework;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30 0030.
 */
public interface HomeworkMapper {
    @Select("select * from homework where id = #{id}")
    Homework selectHomework(Long id);

    List<Homework> selectHomeworkList(HomeworkParam param);

    long count(HomeworkParam param);

    int insertHomework(Homework homework);

    int updateHomework(Homework homework);
}
