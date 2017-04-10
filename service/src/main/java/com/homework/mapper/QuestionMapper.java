package com.homework.mapper;

import com.homework.param.QuestionParam;
import com.homework.model.Question;

import java.util.List;

/**
 * Created by xuke on 2017/4/7 0007.
 */
public interface QuestionMapper {
    int insertQuestion(Question question);
    int updateQuestion(Question question);
    List<Question> selectQuestionList(QuestionParam param);
    Long count(QuestionParam param);
}
