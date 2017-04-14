package com.homework.controller;

import com.homework.model.Studentwork;
import com.homework.param.StudentworkParam;
import com.homework.response.ResponseMsg;
import com.homework.service.StudentworkService;
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
 * @author xuke
 * @create 2017-04-14 下午 15:29
 **/
@RestController
@RequestMapping(value = "/student",produces="application/json;charset=UTF-8")
public class StudentworkController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StudentworkService studentworkService;


    @RequestMapping(value="", method = RequestMethod.POST)
    public Object getStudents(@RequestBody StudentworkParam param) throws Exception{
        ResponseMsg msg = new ResponseMsg();
        List<Studentwork> studentworks = studentworkService.selectList(param);
        msg.setData(JsonUtil.beanToJson(studentworks));
        return msg;
    }

    public static void main(String[] args) throws Exception{
        StudentworkParam param = new StudentworkParam();
        param.setClassId(32323L);
        param.setStudentId(54545L);
        param.setHomeworkId(56656L);
        System.out.println(JsonUtil.beanToJson(param));
    }
}
