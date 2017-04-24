package com.homework.mapper;

import com.homework.model.AnswerLog;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public interface AnswerLogMapper {
    Long insertLog(AnswerLog log);
    int selectTime(Map<String,Long> param);
}
