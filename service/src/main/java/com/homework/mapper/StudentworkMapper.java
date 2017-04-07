package com.homework.mapper;

import com.homework.model.Studentwork;
import com.homework.param.StudentworkParam;

import java.util.List;

/**
 * Created by xuke on 2017/4/7 0007.
 */
public interface StudentworkMapper {
    List<Studentwork> selectStudentworkList(StudentworkParam param);
    Long count(StudentworkParam param);
    int insertStudentwork(Studentwork studentwork);
}
