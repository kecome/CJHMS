package com.homework.service;

import com.homework.data.Page;
import com.homework.mapper.StudentanswerMapper;
import com.homework.model.Studentanswer;
import com.homework.param.StudentanswerParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学生答题service
 *
 * @author xuke
 * @create 2017-04-10 下午 16:00
 **/
@Transactional
@Service
public class StudentAnswerService {
    @Autowired
    private StudentanswerMapper studentanswerMapper;

    public Page<Studentanswer> selectStudentAnswerList(Long homeworkId) {
        StudentanswerParam param = new StudentanswerParam();
        param.setHomeworkId(homeworkId);
        param.setStudentId(234L);
        Page<Studentanswer> page = new Page<>();
        List<Studentanswer> list = studentanswerMapper.selectStudentanswerList(param);
        page.setItems(list);
        page.setTotal(list.size());
        return page;
    }

    public void postStudentanswer(List<Studentanswer> studentanswers) {
        if(studentanswers != null && studentanswers.size() > 0) {
            for(Studentanswer answer : studentanswers) {
                if(answer.getId() == null) {
                    studentanswerMapper.insertStudentanswer(answer);
                }else {
                    studentanswerMapper.updateStudentanswer(answer);
                }
            }
        }
    }

    public void insertStudentanswer(List<Studentanswer> studentanswers) {
        if(studentanswers != null && studentanswers.size() > 0) {
            for(Studentanswer answer : studentanswers) {
                studentanswerMapper.insertStudentanswer(answer);
            }
        }
    }

    public void updateStudentanswer(List<Studentanswer> studentanswers) {
        if(studentanswers != null && studentanswers.size() > 0) {
            for(Studentanswer answer : studentanswers) {
                studentanswerMapper.updateStudentanswer(answer);
            }
        }
    }
}
