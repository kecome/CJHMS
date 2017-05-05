package com.homework.service;

import com.homework.constant.AnswerResult;
import com.homework.constant.MarkType;
import com.homework.data.Page;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.mapper.*;
import com.homework.model.*;
import com.homework.param.QuestionParam;
import com.homework.param.StudentworkParam;
import com.homework.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private StudentanswerMapper studentanswerMapper;
    @Autowired
    private MessageMapper messageMapper;

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
        Homework homework = homeworkMapper.selectHomework(studentwork.getHomeworkId());
        if(homework.getStatus() == null || homework.getStatus().intValue() == 0){
            throw new BusinessException(ErrorInfo.HOMEWORK_NOT_PUBLIC.code, ErrorInfo.HOMEWORK_NOT_PUBLIC.desc);
        }
        //将学生未作答的题目，插入作答表
        QuestionParam param = new QuestionParam();
        param.setStudentId(UserUtil.getUser().getId());
        param.setHomeworkId(homework.getId());
        List<Question> questions = questionMapper.selectQuestionNoAnswer(param);
        if(questions != null && questions.size() > 0) {
            for(Question q : questions) {
                Studentanswer answer = new Studentanswer();
                answer.setIsRight(AnswerResult.ERROR.value);
                answer.setQuestionId(q.getId());
                answer.setComment("未作答");
                answer.setStudentId(param.getStudentId());
                studentanswerMapper.insertStudentanswer(answer);
            }
        }
        //设置学生作业提交时间
        studentwork.setSubmitTime(new Date());
        studentworkMapper.updateStudentwork(studentwork);

        //如果全部是客观题，由前端自动批阅，发送已批阅消息
        if(studentwork.getMark() != null && studentwork.getMark().intValue() == MarkType.YES.value) {
            Message message = new Message();
            message.setTeacherId(homework.getTeacherId());
            message.setType(0);
            message.setIsRead(0);
            message.setStudentId(param.getStudentId());
            message.setContent("你提交的" + homework.getTitle() + "老师已经批阅啦，快去看看吧");
            message.setResourceId(homework.getId());
            messageMapper.insertMessage(message);
        }
    }
}
