package com.homework.service;

import com.homework.data.Page;
import com.homework.mapper.QuestionMapper;
import com.homework.model.Question;
import com.homework.param.QuestionParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作业题目service
 *
 * @author xuke
 * @create 2017-04-10 下午 14:02
 **/
@Transactional
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 题目列表查询
     * @param param
     * @return
     */
    public Page<Question> selectQuestionList(QuestionParam param) {
        Page<Question> page = new Page<>();
        List<Question> list = questionMapper.selectQuestionList(param);
        page.setItems(list);
        if(param.getIsPage()) {
            page.setTotal(questionMapper.count(param));
            return page;
        }
        page.setTotal(list.size());
        return page;
    }

    public Question selectQuestion(Long id) {
        return questionMapper.selectQuestion(id);
    }
}
