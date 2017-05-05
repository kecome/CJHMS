package com.homework.mapper;

import com.homework.model.Studentanswer;
import com.homework.param.MarkParam;
import com.homework.param.StudentanswerParam;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xuke on 2017/4/10 0010.
 */
public interface StudentanswerMapper {
    @Select("select * from studentAnswer where id = #{id}")
    Studentanswer selectStudentAnswer(Long id);
    List<Studentanswer> selectStudentanswerList(StudentanswerParam param);
    int insertStudentanswer(Studentanswer studentanswer);
    int updateStudentanswer(Studentanswer studentanswer);
    int markStudentanswer(MarkParam param);
    int updateAnswer(Studentanswer studentanswer);
    Studentanswer selectAnswer(StudentanswerParam param);
}
