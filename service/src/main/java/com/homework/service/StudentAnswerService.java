package com.homework.service;

import com.homework.data.Page;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.mapper.*;
import com.homework.model.AnswerLog;
import com.homework.model.Homework;
import com.homework.model.Message;
import com.homework.model.Studentanswer;
import com.homework.param.MarkParam;
import com.homework.param.StudentanswerLog;
import com.homework.param.StudentanswerParam;
import com.homework.util.UserUtil;
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
    @Autowired
    private StudentworkMapper studentworkMapper;
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private AnswerLogMapper answerLogMapper;
    @Autowired
    private QuestionMapper questionMapper;

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
     * @param
     * @return
     */
    public Studentanswer postStudentanswer(StudentanswerLog param) {
        Studentanswer studentanswer = param.getStudentanswer();
        AnswerLog answerLog = param.getAnswerLog();
        if(studentanswer == null) {
            return null;
        }
        //题目不存在
        if(questionMapper.selectQuestion(studentanswer.getQuestionId()) == null){
            throw new BusinessException(ErrorInfo.QUESTION_IS_NULL.code, ErrorInfo.QUESTION_IS_NULL.desc);
        }
        if(studentanswer.getStudentId() == null)studentanswer.setStudentId(UserUtil.getUser().getId());
        if(studentanswer.getId() == null) {
            studentanswerMapper.insertStudentanswer(studentanswer);
        }else {
            studentanswerMapper.updateStudentanswer(studentanswer);
        }
        if(answerLog != null) {
            if(answerLog.getStudentId() == null) answerLog.setStudentId(UserUtil.getUser().getId());
            answerLog.setQuestionId(studentanswer.getQuestionId());
            answerLogMapper.insertLog(answerLog);
        }
//        if(studentanswers != null && studentanswers.size() > 0) {
//            for(Studentanswer answer : studentanswers) {
//                if(UserUtil.getUser().getRole().equals(UserUtil.STUDENT) && answer.getStudentId() == null) {
//                    answer.setStudentId(UserUtil.getUser().getId());
//                }
//                if(answer.getId() == null) {
//                    studentanswerMapper.insertStudentanswer(answer);
//                    aIds.add(answer.getId());
//                }else {
//                    studentanswerMapper.updateStudentanswer(answer);
//                    aIds.add(answer.getId());
//                }
//            }
//        }
//        return aIds;
        return studentanswer;
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
                if(answer.getId() == null) {
                    studentanswerMapper.insertStudentanswer(answer);
                } else {
                    studentanswerMapper.updateStudentanswer(answer);
                }
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
