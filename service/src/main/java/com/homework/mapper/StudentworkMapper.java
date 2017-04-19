package com.homework.mapper;

import com.homework.model.Homework;
import com.homework.model.Studentwork;
import com.homework.param.StudentworkParam;

import java.util.List;

/**
 * Created by xuke on 2017/4/7 0007.
 */
public interface StudentworkMapper {
    List<Studentwork> selectStudentworkList(StudentworkParam param);
    List<Studentwork> selectList(StudentworkParam param);
    Long count(StudentworkParam param);
    int insertStudentwork(Studentwork studentwork);
    int deleteStudentwork(Long homeworkId);
    int updateStudentwork(Studentwork studentwork);
    List<Long> selectStudentId(Long homeworkId);

    List<Homework> selectHomework(StudentworkParam param);
    long countHomework(StudentworkParam param);
}
