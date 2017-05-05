package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Studentanswer;
import com.homework.param.MarkParam;
import com.homework.param.QuestionIndexParam;
import com.homework.param.StudentanswerLog;
import com.homework.param.StudentanswerParam;
import com.homework.response.ResponseMsg;
import com.homework.service.QuestionIndexService;
import com.homework.service.StudentAnswerService;
import com.homework.util.JsonUtil;
import com.homework.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生答题controller
 *
 * @author xuke
 * @create 2017-04-10 下午 14:57
 **/
@Api(value="StudentAnswer-api",description="与答案相关操作")
@RestController
@RequestMapping(value = "/studentanswer",produces="application/json;charset=UTF-8")
public class StudentAnswerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudentAnswerService studentAnswerService;
    @Autowired
    private QuestionIndexService questionIndexService;

    @ApiOperation(value="获取学生答案",notes="根据答案id")
    @RequestMapping(value="", method = RequestMethod.GET)
    public ResponseMsg<Page<Studentanswer>> getStudentAnswer(@ApiParam("答案id") Long id) throws Exception{
        logger.info("请求方法getStudentAnswer参数---->" + id);
        Page<Studentanswer> page = studentAnswerService.selectStudentAnswerList(id);
        ResponseMsg<Page<Studentanswer>> msg = new ResponseMsg<>();
        msg.setData(page);
        logger.info("请求方法getStudentAnswer返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    /**
     * 学生提交答案
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value="学生提交答案")
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseMsg<Studentanswer> postStudentAnswer(@ApiParam("学生答题记录") @RequestBody StudentanswerLog answerLog) throws Exception {
        logger.info("请求方法PostStudentAnswer参数---->" + JsonUtil.beanToJson(answerLog));
        Studentanswer studentanswer = studentAnswerService.postStudentanswer(answerLog);
        ResponseMsg<Studentanswer> msg = new ResponseMsg();
        msg.setData(studentanswer);
        logger.info("请求方法PostStudentAnswer返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    /**
     * 老师批阅作业
     * @param param
     * @return
     * @throws Exception
     */
    @ApiOperation(value="老师批阅作业",response=ResponseMsg.class)
    @RequestMapping(value="/mark", method = RequestMethod.POST)
    public Object markAnswer(@ApiParam("学生答题实体类") @RequestBody List<Studentanswer> param) throws Exception{
        logger.info("请求方法markAnswer参数---->" + JsonUtil.beanToJson(param));
        ResponseMsg msg = new ResponseMsg();
        studentAnswerService.updateStudentanswer(param);
        logger.info("请求方法markAnswer返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    /**
     * 确认批阅
     * @param param
     * @return
     * @throws Exception
     */
    @ApiOperation(value="确认批阅",response=ResponseMsg.class)
    @RequestMapping(value="/markSubmit", method = RequestMethod.POST)
    public Object markSubmit(@ApiParam("批阅参数") @RequestBody MarkParam param) throws Exception{
        logger.info("请求方法markSubmit参数---->" + JsonUtil.beanToJson(param));
        studentAnswerService.markSubmit(param);
        ResponseMsg msg = new ResponseMsg();
        logger.info("请求方法markSubmit返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }
    @ApiOperation(value="更新问题序号")
    @RequestMapping(value="/index", method = RequestMethod.POST)
    public ResponseMsg<Long> index(@ApiParam("问题序号参数") @RequestBody QuestionIndexParam index) throws Exception {
        logger.info("请求方法index参数---->" + JsonUtil.beanToJson(index));
        if(index.getStudentId() == null) index.setStudentId(UserUtil.getUser().getId());
        Long indexId  = questionIndexService.updateIndex(index);
        ResponseMsg<Long> msg = new ResponseMsg();
        msg.setData(indexId);
        logger.info("请求方法index返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    @ApiIgnore
    @RequestMapping(value="homework", method = RequestMethod.POST)
    public Object getStudentAnswerWork(@RequestBody StudentanswerParam param) {
        return null;
    }

    public static void main(String[] args) throws Exception{
        List<Studentanswer> list = new ArrayList<>();

        Studentanswer studentanswer = new Studentanswer();
        studentanswer.setIsRight(1);
        studentanswer.setAnswer("答案");
        studentanswer.setStudentId(232332L);
        studentanswer.setComment("好评");
        studentanswer.setQuestionId(5555L);

        list.add(studentanswer);

        System.out.println(JsonUtil.beanToJson(list));

    }

}
