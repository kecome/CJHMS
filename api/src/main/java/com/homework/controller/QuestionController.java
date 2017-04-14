package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Question;
import com.homework.param.QuestionParam;
import com.homework.response.ResponseMsg;
import com.homework.service.QuestionService;
import com.homework.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/question",produces="application/json;charset=UTF-8")
public class QuestionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
        logger.info("请求方法getQuestionList参数---->" + JsonUtil.beanToJson(param));
        Page<Question> page = questionService.selectQuestionList(param);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(JsonUtil.beanToJson(page));
        logger.info("请求方法getQuestionList返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public Object getQuestion(Long id) throws Exception{
        logger.info("请求方法getQuestion参数---->" + id);
        Question question = questionService.selectQuestion(id);
        String data = JsonUtil.beanToJson(question);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(data);
        logger.info("请求方法getQuestion返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

//    public static void main(String[] args) throws Exception {
//        QuestionParam param = new QuestionParam();
//        param.setHomeworkId(43434L);
//        Map<String, String> order = new HashMap<>();
//        order.put("created", "desc");
//        param.setOrder(order);
//        System.out.println(JsonUtil.beanToJson(param));
//    }

}
