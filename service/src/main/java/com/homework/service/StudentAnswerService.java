package com.homework.service;

import com.homework.data.Page;
import com.homework.mapper.MessageMapper;
import com.homework.mapper.StudentanswerMapper;
import com.homework.mapper.StudentworkMapper;
import com.homework.model.Message;
import com.homework.model.Studentanswer;
import com.homework.param.MarkParam;
import com.homework.param.StudentanswerParam;
import com.homework.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private StudentworkMapper studentworkMapper;
    @Autowired
    private MessageMapper messageMapper;


    public Page<Studentanswer> selectStudentAnswerList(Long homeworkId) {
        StudentanswerParam param = new StudentanswerParam();
        param.setHomeworkId(homeworkId);
        param.setStudentId(UserUtil.getUser().getId());
        Page<Studentanswer> page = new Page<>();
        List<Studentanswer> list = studentanswerMapper.selectStudentanswerList(param);
        page.setItems(list);
        page.setTotal(list.size());
        return page;
    }

    public void postStudentanswer(List<Studentanswer> studentanswers) {
        if(studentanswers != null && studentanswers.size() > 0) {
            for(Studentanswer answer : studentanswers) {
                if(UserUtil.getUser().getRole().equals(UserUtil.STUDENT))answer.setStudentId(UserUtil.getUser().getId());
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

    public void markAnswer(List<MarkParam> param) {
        Set<Long> sIds = new HashSet<>();
        Message message = new Message();
        message.setTeacherId(UserUtil.getUser().getId());
        message.setType(0);
        message.setStudentId(1234L);
        messageMapper.insertMessage(message);
        if(param != null && param.size() > 0){
            for(MarkParam p : param) {
                studentanswerMapper.markStudentanswer(p);
            }
        }
    }
}
