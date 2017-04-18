package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Studentwork;
import com.homework.param.StudentworkParam;
import com.homework.response.ResponseMsg;
import com.homework.service.StudentworkService;
import com.homework.util.JsonUtil;
import com.homework.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 我的作业
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="", method = RequestMethod.POST)
    public Object getStudents(@RequestBody StudentworkParam param) throws Exception{
        logger.info("请求方法getStudents参数---->" + JsonUtil.beanToJson(param));
        ResponseMsg msg = new ResponseMsg();
        if(UserUtil.getUser().getRole().equals(UserUtil.STUDENT)) {   //当前登录人是学生
            param.setStudentId(UserUtil.getUser().getId());
        }
        if(UserUtil.getUser().getRole().equals(UserUtil.TEACHER)) {   //当前登录人是老师
            param.setTeacherId(UserUtil.getUser().getId());
        }
        Page<Studentwork> page = studentworkService.selectList(param);
        msg.setData(JsonUtil.beanToJson(page));
        logger.info("请求方法getStudents返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }


//    public static void main(String[] args) throws Exception{
//        StudentworkParam param = new StudentworkParam();
//        param.setClassId(32323L);
//        param.setStudentId(54545L);
//        param.setHomeworkId(56656L);
//        System.out.println(JsonUtil.beanToJson(param));
//    }
}
