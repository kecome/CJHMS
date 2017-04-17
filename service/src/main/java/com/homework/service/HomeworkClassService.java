package com.homework.service;

import com.homework.data.Page;
import com.homework.mapper.HomeworkClassMapper;
import com.homework.model.HomeworkClass;
import com.homework.param.HomeworkClassParam;
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

    public Page<HomeworkClass> selectList(HomeworkClassParam param) {
        Page<HomeworkClass> page = new Page<>();
        List<HomeworkClass> list = homeworkClassMapper.selectClassList(param);
        page.setItems(list);
        if(param.getIsPage()) {
            page.setTotal(homeworkClassMapper.count(param));
            return page;
        }
        page.setTotal(list.size());
        return page;

    }
}
