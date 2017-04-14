package com.homework.service;

import com.homework.mapper.HomeworkClassMapper;
import com.homework.model.HomeworkClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xuke
 * @create 2017-04-07 上午 9:58
 **/
@Transactional
@Service
public class HomeworkClassService {
    @Autowired
    private HomeworkClassMapper homeworkClassMapper;

    public Long insertHomeworkClass(HomeworkClass homeworkClass) {
        return homeworkClassMapper.insertHomeworkClass(homeworkClass);
    }

    public List<HomeworkClass> selectList(Long homeworkId) {
        return homeworkClassMapper.selectList(homeworkId);
    }
}
