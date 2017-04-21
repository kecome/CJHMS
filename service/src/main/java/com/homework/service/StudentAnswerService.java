package com.homework.service;

import com.homework.data.Page;
import com.homework.mapper.HomeworkMapper;
import com.homework.mapper.MessageMapper;
import com.homework.mapper.StudentanswerMapper;
import com.homework.mapper.StudentworkMapper;
import com.homework.model.Homework;
import com.homework.model.Message;
import com.homework.model.Studentanswer;
import com.homework.param.MarkParam;
import com.homework.param.StudentanswerParam;
import com.homework.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private StudentworkMapper studentworkMapper;
    @Autowired
    private HomeworkMapper homeworkMapper;
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

    /**
     * 学生
     * @param studentanswers
     * @return
     */
    public List<Long> postStudentanswer(List<Studentanswer> studentanswers) {
        List<Long> aIds = new ArrayList<>();
        if(studentanswers != null && studentanswers.size() > 0) {
            for(Studentanswer answer : studentanswers) {
                if(UserUtil.getUser().getRole().equals(UserUtil.STUDENT) && answer.getStudentId() == null) {
                    answer.setStudentId(UserUtil.getUser().getId());
                }
                if(answer.getId() == null) {
                    studentanswerMapper.insertStudentanswer(answer);
                    aIds.add(answer.getId());
                }else {
                    studentanswerMapper.updateStudentanswer(answer);
                    aIds.add(answer.getId());
                }
            }
        }
        return aIds;
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
        if(param != null && param.size() > 0){
            for(MarkParam p : param) {
                studentanswerMapper.markStudentanswer(p);
            }
            Long studentId = param.get(0).getStudentId();
            Long homeworkId = param.get(0).getHomeworkId();
            Homework homework = homeworkMapper.selectHomework(homeworkId);
            Message message = new Message();
            message.setTeacherId(UserUtil.getUser().getId());
            message.setType(0);
            message.setStudentId(studentId);
            message.setContent("你提交的" + homework.getTitle() + "老师已经批阅啦，快去看看吧");
            message.setResourceId(homeworkId);
            messageMapper.insertMessage(message);
        }
    }

    public void markSubmit(MarkParam param) {
        Homework homework = homeworkMapper.selectHomework(param.getHomeworkId());
        Message message = new Message();
        message.setTeacherId(UserUtil.getUser().getId());
        message.setType(0);
        message.setStudentId(param.getStudentId());
        message.setContent("你提交的" + homework.getTitle() + "老师已经批阅啦，快去看看吧");
        message.setResourceId(param.getHomeworkId());
        messageMapper.insertMessage(message);
    }
}
