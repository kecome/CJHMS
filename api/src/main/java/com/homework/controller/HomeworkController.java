package com.homework.controller;

import com.homework.data.HomeworkParam;
import com.homework.data.Page;
import com.homework.exception.BusinessException;
import com.homework.exception.ErrorInfo;
import com.homework.model.Homework;
import com.homework.model.HomeworkClass;
import com.homework.response.ResponseMsg;
import com.homework.service.HomeworkClassService;
import com.homework.service.HomeworkService;
import com.homework.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * 作业controller
 *
 * @author xuke
 * @create 2017-03-30 下午 19:22
 **/
@Api("HomeworkController控制器")
@RestController
@RequestMapping(value = "/homework")
public class HomeworkController {
    Logger logger = Logger.getLogger(HomeworkController.class.getName());
    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private HomeworkClassService homeworkClassService;


    @RequestMapping(value = "postHC")
    public Object postHomeClass(@RequestBody HomeworkClass homeworkClass) {
        homeworkClassService.insertHomeworkClass(homeworkClass);
        return new ResponseMsg();
    }

    @ApiOperation(value = "获取作业列表",notes = "直接请求")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Object getHomeworkList(@RequestBody HomeworkParam param) throws Exception{
        Page<Homework> page = homeworkService.selectHomeworkList(param);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(JsonUtil.beanToJson(page));
        return msg;
    }

    @ApiOperation(value = "根据id获取作业", httpMethod = "GET" ,notes = "携带作业id")
    @RequestMapping(value="", method = RequestMethod.GET)
    public Object getHomework(Long id) throws Exception{
        if(id == null || id < 0) {
            throw new BusinessException(ErrorInfo.ID_IS_NULL.code, ErrorInfo.ID_IS_NULL.desc);
        }
        Homework hk = homeworkService.selectHomework(id);
        String data = JsonUtil.beanToJson(hk);
        ResponseMsg msg = new ResponseMsg();
        msg.setData(data);
        return msg;
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public Object postHomework(@Validated @RequestBody Homework homework) throws Exception {
        System.out.println(homework);
        homeworkService.insertHomework(homework);
        return new ResponseMsg();
    }


//    public static void main(String[] args) throws  Exception {
//        HomeworkClass hc = new HomeworkClass();
//        hc.setClassId(2323543L);
//        hc.setHomeworkId(435545L);
//        System.out.println(JsonUtil.beanToJson(hc));
//    }

}
