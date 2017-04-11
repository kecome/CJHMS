package com.homework.mapper;

import com.homework.model.Studentanswer;
import com.homework.param.StudentanswerParam;

import java.util.List;

/**
 * Created by xuke on 2017/4/10 0010.
 */
public interface StudentanswerMapper {
    List<Studentanswer> selectStudentanswerList(StudentanswerParam param);
    int insertStudentanswer(Studentanswer studentanswer);
}
