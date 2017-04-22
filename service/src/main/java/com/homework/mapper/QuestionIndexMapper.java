package com.homework.mapper;

import com.homework.model.QuestionIndex;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public interface QuestionIndexMapper {
    Long updateIndex(QuestionIndex index);
    Long insertIndex(QuestionIndex index);
    QuestionIndex selectIndex(Long studentworkId);
}
