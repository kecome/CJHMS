package com.homework.mapper;

import com.homework.model.Question;
import com.homework.param.QuestionParam;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xuke on 2017/4/7 0007.
 */
public interface QuestionMapper {
    @Select("select * from question where id = #{id}")
    Question selectQuestion(Long id);
    int insertQuestion(Question question);
    int updateQuestion(Question question);
    List<Question> selectQuestionList(QuestionParam param);
    Long count(QuestionParam param);
    int deleteQuestion(Long homeworkId);
    List<Question> selectList(Long homeworkId);
}
