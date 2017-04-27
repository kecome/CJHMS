package com.homework.service;

import com.homework.data.Page;
import com.homework.mapper.StudentworkMapper;
import com.homework.model.Homework;
import com.homework.model.Studentwork;
import com.homework.param.StudentworkParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xuke
 * @create 2017-04-14 下午 15:53
 **/
@Transactional
@Service
public class StudentworkService {
    @Autowired
    private StudentworkMapper studentworkMapper;

    public Page<Studentwork> selectList(StudentworkParam param) {
        Page<Studentwork> page = new Page<>();
        List<Studentwork> list = studentworkMapper.selectStudentworkList(param);
        page.setItems(list);
        if(param.getIsPage()) {
            page.setTotal(studentworkMapper.count(param));
            return page;
        }
        page.setTotal(list.size());
        return page;
    }

    public Page<Homework> selectHomework(StudentworkParam param) {
        Page<Homework> page = new Page<>();
        List<Homework> list = studentworkMapper.selectHomework(param);
        page.setItems(list);
        if(param.getIsPage()) {
            page.setTotal(studentworkMapper.countHomework(param));
            return page;
        }
        page.setTotal(list.size());
        return page;
    }

    public Page<Homework> selectHomeworkRecord(StudentworkParam param) {
        Page<Homework> page = new Page<>();
        List<Homework> list = studentworkMapper.selectHomeworkRecord(param);
        page.setItems(list);
        if(param.getIsPage()) {
            page.setTotal(studentworkMapper.countHomeworkRecord(param));
            return page;
        }
        page.setTotal(list.size());
        return page;
    }

    public void submitHomework(Studentwork studentwork) {
        studentworkMapper.updateStudentwork(studentwork);
    }
}
