package com.homework.service;

import com.homework.mapper.StudentworkMapper;
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

    public List<Studentwork> selectList(StudentworkParam param) {
        return studentworkMapper.selectList(param);
    }
}
