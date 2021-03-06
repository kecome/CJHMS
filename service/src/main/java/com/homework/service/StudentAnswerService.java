package com.homework.service;

import com.homework.constant.AnswerResult;
import com.homework.constant.QuestionType;
import com.homework.data.Page;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.mapper.*;
import com.homework.model.*;
import com.homework.param.MarkParam;
import com.homework.param.StudentanswerLog;
import com.homework.param.StudentanswerParam;
import com.homework.param.StudentworkParam;
import com.homework.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Question q = questionMapper.selectQuestion(studentanswer.getQuestionId());
        //题目不存在
        if(q == null){
            throw new BusinessException(ErrorInfo.QUESTION_IS_NULL.code, ErrorInfo.QUESTION_IS_NULL.desc);
        }
        if(studentanswer.getStudentId() == null)studentanswer.setStudentId(UserUtil.getUser().getId());
        StudentworkParam sParam = new StudentworkParam();
        sParam.setStudentId(studentanswer.getStudentId());
        sParam.setHomeworkId(q.getHomeworkId());
        Studentwork work = studentworkMapper.selectStudentwork(sParam);
        if(work.getSubmit() != null && work.getSubmit().intValue() == 1) {
            throw new BusinessException(ErrorInfo.HOMEWORK_SUBMIT.code, ErrorInfo.HOMEWORK_SUBMIT.desc);
        }
        if(q.getType().intValue() == QuestionType.SINGLE.value || q.getType().intValue() == QuestionType.MULTIPLE.value ) {  //客观题，自动批阅
            if(StringUtils.isNotEmpty(studentanswer.getAnswer())) {
                char[] sAnswer = studentanswer.getAnswer().toUpperCase().toCharArray();
                char[] qAnswer = q.getAnswer().toUpperCase().toCharArray();
                Arrays.sort(sAnswer);
                Arrays.sort(qAnswer);
                if(String.valueOf(sAnswer).equals(String.valueOf(qAnswer))) {
                    studentanswer.setIsRight(AnswerResult.RIGHT.value);  //正确
                } else {
                    studentanswer.setIsRight(AnswerResult.ERROR.value);  //错误
                }
            }else {
                studentanswer.setIsRight(AnswerResult.ERROR.value);  //错误
            }
        }

        StudentanswerParam p = new StudentanswerParam();
        p.setQuestionId(studentanswer.getQuestionId());
        p.setStudentId(studentanswer.getStudentId());
        if(studentanswerMapper.selectAnswer(p) == null) {
            studentanswerMapper.insertStudentanswer(studentanswer);
        }else {
            studentanswerMapper.updateAnswer(studentanswer);
        }
//        if(studentanswer.getId() == null) {
//            studentanswerMapper.insertStudentanswer(studentanswer);
//        }else {
//            studentanswerMapper.updateStudentanswer(studentanswer);
//        }
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
                if(answer.getId() == null || studentanswerMapper.selectStudentAnswer(answer.getId()) == null) {
                    throw new BusinessException(ErrorInfo.STUDENTANSAWER_IS_NULL.code, ErrorInfo.STUDENTANSAWER_IS_NULL.desc);
                }
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
        Map<String, Long> map = new HashMap<>();
        map.put("studentId", param.getStudentId());
        map.put("homeworkId", param.getHomeworkId());
        studentworkMapper.updateMark(map);
        Homework homework = homeworkMapper.selectHomework(param.getHomeworkId());
        Message message = new Message();
        message.setTeacherId(UserUtil.getUser().getId());
        message.setType(0);
        message.setIsRead(0);
        message.setStudentId(param.getStudentId());
        message.setContent("你提交的" + homework.getTitle() + "老师已经批阅啦，快去看看吧");
        message.setResourceId(param.getHomeworkId());
        messageMapper.insertMessage(message);
    }

    public static void main(String[] args) {
        String str1 = "adcb";
        String str2 = "cabd";
        char[] s1 = str1.toUpperCase().toCharArray();
        Arrays.sort(s1);
        System.out.println(s1);

        char[] s2 = str2.toUpperCase().toCharArray();
        Arrays.sort(s2);

        System.out.println(s2);
        System.out.println(String.valueOf(s1).equals(String.valueOf(s2)));

    }
}
