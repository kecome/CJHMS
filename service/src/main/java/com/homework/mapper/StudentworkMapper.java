package com.homework.mapper;

import com.homework.model.Homework;
import com.homework.model.Studentwork;
import com.homework.param.StudentworkParam;

import java.util.List;
import java.util.Map;

/**
 * Created by xuke on 2017/4/7 0007.
 */
public interface StudentworkMapper {
    List<Studentwork> selectStudentworkList(StudentworkParam param);
    List<Studentwork> selectList(StudentworkParam param);
    Integer count(StudentworkParam param);
    int insertStudentwork(Studentwork studentwork);
    int deleteStudentwork(Long homeworkId);
    int updateStudentwork(Studentwork studentwork);
    List<Long> selectStudentId(Long homeworkId);

    List<Homework> selectHomework(StudentworkParam param);
    Integer countHomework(StudentworkParam param);

    List<Homework> selectHomeworkRecord(StudentworkParam param);
    Integer countHomeworkRecord(StudentworkParam param);

    int updateSubmit(Map<String, Long> param);
    int updateMark(Map<String, Long> param);
    Studentwork selectStudentwork(StudentworkParam param);
}
