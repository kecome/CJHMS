package com.homework.service;

import com.homework.mapper.HomeworkClassMapper;
import com.homework.model.HomeworkClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xuke
 * @create 2017-04-07 上午 9:58
 **/
@Transactional
@Service
public class HomeworkClassService {
    @Autowired
    private HomeworkClassMapper homeworkClassMapper;

    public int insertHomeworkClass(HomeworkClass homeworkClass) {
        return homeworkClassMapper.insertHomeworkClass(homeworkClass);
    }
}
