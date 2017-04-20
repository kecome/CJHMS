package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Studentanswer;
import com.homework.param.MarkParam;
import com.homework.param.StudentanswerParam;
import com.homework.response.ResponseMsg;
import com.homework.service.StudentAnswerService;
import com.homework.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学生答题controller
 *
 * @author xuke
 * @create 2017-04-10 下午 14:57
 **/
@RestController
@RequestMapping(value = "/studentanswer",produces="application/json;charset=UTF-8")
public class StudentAnswerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudentAnswerService studentAnswerService;

    @RequestMapping(value="", method = RequestMethod.GET)
    public Object getStudentAnswer(Long id) throws Exception{
        logger.info("请求方法getStudentAnswer参数---->" + id);
        Page<Studentanswer> page = studentAnswerService.selectStudentAnswerList(id);
        ResponseMsg<Page> msg = new ResponseMsg<>();
        msg.setData(page);
        logger.info("请求方法getStudentAnswer返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public Object postStudentAnswer(@RequestBody List<Studentanswer> studentanswers) throws Exception {
        logger.info("请求方法PostStudentAnswer参数---->" + JsonUtil.beanToJson(studentanswers));
        studentAnswerService.postStudentanswer(studentanswers);
        ResponseMsg msg = new ResponseMsg();
        logger.info("请求方法PostStudentAnswer返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    /**
     * 老师批阅作业
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mark", method = RequestMethod.POST)
    public Object markAnswer(List<MarkParam> param) throws Exception{
        logger.info("请求方法markAnswer参数---->" + JsonUtil.beanToJson(param));
        ResponseMsg msg = new ResponseMsg();
        studentAnswerService.markAnswer(param);
        logger.info("请求方法markAnswer返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    @RequestMapping(value="homework", method = RequestMethod.POST)
    public Object getStudentAnswerWork(@RequestBody StudentanswerParam param) {
        return null;
    }

//    public static void main(String[] args) throws Exception{
//        System.out.println(buildStudentAnswer());
//    }

}
