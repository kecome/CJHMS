package com.homework.service;

import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.mapper.QuestionIndexMapper;
import com.homework.mapper.QuestionMapper;
import com.homework.mapper.StudentworkMapper;
import com.homework.model.QuestionIndex;
import com.homework.model.Studentwork;
import com.homework.param.QuestionIndexParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xuke
 * @create 2017-04-21 下午 21:10
 **/
@Service
@Transactional
public class QuestionIndexService {
    @Autowired
    private QuestionIndexMapper questionIndexMapper;
    @Autowired
    private StudentworkMapper studentworkMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public Long updateIndex(QuestionIndexParam index) {
        //作业题目不存在
        if(questionMapper.selectQuestion(index.getQuestionId()) == null) {
            throw new BusinessException(ErrorInfo.QUESTION_IS_NULL.code, ErrorInfo.QUESTION_IS_NULL.desc);
        }
        Studentwork work = new Studentwork();
        work.setId(index.getStudentworkId());
        work.setTime(index.getTime());
        int count = studentworkMapper.updateStudentwork(work);
        //作业不存在
        if(count == 0) return null;
        QuestionIndex qIndex = questionIndexMapper.selectIndex(index.getStudentworkId());

        if(qIndex == null) {
            qIndex = new QuestionIndex();
            qIndex.setStudentworkId(index.getStudentworkId());
            qIndex.setQuestionId(index.getQuestionId());
            questionIndexMapper.insertIndex(qIndex);
        }else {
            qIndex.setQuestionId(index.getQuestionId());
            questionIndexMapper.updateIndex(qIndex);
        }
        return qIndex.getId();
    }
}
