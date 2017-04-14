package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Studentanswer;
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

import java.util.ArrayList;
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
        String data = JsonUtil.beanToJson(page);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(data);
        logger.info("请求方法getStudentAnswer返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public Object PostStudentAnswer(@RequestBody List<Studentanswer> studentanswers) throws Exception {
        logger.info("请求方法PostStudentAnswer参数---->" + JsonUtil.beanToJson(studentanswers));
        studentAnswerService.insertStudentanswer(studentanswers);
        ResponseMsg msg = new ResponseMsg();
        logger.info("请求方法PostStudentAnswer返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

//    public static void main(String[] args) throws Exception{
//        System.out.println(buildStudentAnswer());
//    }

    public static String buildStudentAnswer() throws Exception {
        List<Studentanswer> list = new ArrayList<>();
        Studentanswer s1 = new Studentanswer();
        s1.setQuestionId(1123L);
        s1.setStudentId(345L);
        s1.setAnswer("答案。。。");
        s1.setIsRight(0);
        s1.setComment("老师批阅");
        list.add(s1);

        Studentanswer s2 = new Studentanswer();
        s2.setQuestionId(1124L);
        s2.setStudentId(345L);
        s2.setAnswer("答案。。。");
        s2.setIsRight(1);
        s2.setComment("老师批阅砖石");
        list.add(s2);
        return JsonUtil.beanToJson(list);
    }

}
