package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Question;
import com.homework.param.QuestionParam;
import com.homework.response.ResponseMsg;
import com.homework.service.QuestionService;
import com.homework.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作业题目controller
 *
 * @author xuke
 * @create 2017-04-10 下午 14:00
 **/
@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取题目列表
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object getQuestionList( @RequestBody QuestionParam param) throws Exception{
        Page<Question> page = questionService.selectQuestionList(param);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(JsonUtil.beanToJson(page));
        return msg;
    }

}
