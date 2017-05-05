package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Question;
import com.homework.param.QuestionParam;
import com.homework.response.ResponseMsg;
import com.homework.service.QuestionService;
import com.homework.util.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
@Api(value="Question-api",description="获取题目相关操作")
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
    @ApiOperation(value="获取题目列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseMsg<Page<Question>> getQuestionList(@ApiParam("题目参数") @RequestBody QuestionParam param) throws Exception{
        logger.info("请求方法getQuestionList参数---->" + JsonUtil.beanToJson(param));
        Page<Question> page = questionService.selectQuestionList(param);
        ResponseMsg<Page<Question>> msg = new ResponseMsg<>();
        msg.setData(page);
        logger.info("请求方法getQuestionList返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }
    @ApiOperation(value="根据题目id获取题目")
    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseMsg<Question> getQuestion(@ApiParam("题目id") Long id) throws Exception{
        logger.info("请求方法getQuestion参数---->" + id);
        Question question = questionService.selectQuestion(id);
        ResponseMsg<Question> msg = new ResponseMsg<>();
        msg.setData(question);
        logger.info("请求方法getQuestion返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

//    public static void main(String[] args) throws Exception {
//        QuestionParam param = new QuestionParam();
//        param.setHomeworkId(43434L);
//        Map<String, String> order = new jjHashMap<>();
//        order.put("created", "desc");
//        param.setOrder(order);
//        System.out.println(JsonUtil.beanToJson(param));
//    }

}
