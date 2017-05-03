package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Homework;
import com.homework.model.Studentwork;
import com.homework.param.StudentworkParam;
import com.homework.response.ResponseMsg;
import com.homework.service.StudentworkService;
import com.homework.util.JsonUtil;
import com.homework.util.UserUtil;

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
 * @author xuke
 * @create 2017-04-14 下午 15:29
 **/
@Api(value="Studentwork-api",description="学生作业控制器")
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
    @ApiOperation(value="获取我的作业")
    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseMsg<Page<Studentwork>> getStudentwork(@ApiParam("学生作业参数") @RequestBody StudentworkParam param) throws Exception{
        logger.info("请求方法getStudents参数---->" + JsonUtil.beanToJson(param));
        if(param.getStudentId() == null && UserUtil.getUser().getRole().equals(UserUtil.STUDENT)) {   //当前登录人是学生
            param.setStudentId(UserUtil.getUser().getId());
        }
        if(param.getTeacherId() == null && UserUtil.getUser().getRole().equals(UserUtil.TEACHER)) {   //当前登录人是老师
            param.setTeacherId(UserUtil.getUser().getId());
        }
        Page<Studentwork> page = studentworkService.selectList(param);
        ResponseMsg<Page<Studentwork>> msg = new ResponseMsg<>();
        msg.setData(page);
        logger.info("请求方法getStudents返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }
    @ApiOperation(value="获取作业列表")
    @RequestMapping(value="list", method = RequestMethod.POST)
    public ResponseMsg<Page<Homework>> getHomework(@ApiParam("学生作业参数") @RequestBody StudentworkParam param) throws Exception{
        logger.info("请求方法getHomework参数---->" + JsonUtil.beanToJson(param));
        if(param.getStudentId() == null) param.setStudentId(UserUtil.getUser().getId());
        Page<Homework> page = studentworkService.selectHomework(param);
        ResponseMsg<Page<Homework>> msg = new ResponseMsg<>();
        msg.setData(page);
        logger.info("请求方法getHomework返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    @ApiOperation(value="获取作业记录")
    @RequestMapping(value="record", method = RequestMethod.POST)
    public ResponseMsg<Page<Homework>> getHomeworkRecord(@ApiParam("作业记录参数") @RequestBody StudentworkParam param) throws Exception{
        logger.info("请求方法getHomework参数---->" + JsonUtil.beanToJson(param));
        if(param.getStudentId() == null) param.setStudentId(UserUtil.getUser().getId());
        Page<Homework> page = studentworkService.selectHomeworkRecord(param);
        ResponseMsg<Page<Homework>> msg = new ResponseMsg<>();
        msg.setData(page);
        logger.info("请求方法getHomework返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

    @ApiOperation(value="提交学生作业",response=ResponseMsg.class)
    @RequestMapping(value="submit", method = RequestMethod.POST)
    public Object submitHomework(@ApiParam("学生作业实体") @RequestBody Studentwork studentwork) throws Exception {
        if(studentwork.getStudentId() == null) studentwork.setStudentId(UserUtil.getUser().getId());
        logger.info("请求方法submitHomework参数---->" + JsonUtil.beanToJson(studentwork));
        ResponseMsg msg = new ResponseMsg();
        studentworkService.submitHomework(studentwork);
        logger.info("请求方法submitHomework返回---->" + JsonUtil.beanToJson(msg));
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
