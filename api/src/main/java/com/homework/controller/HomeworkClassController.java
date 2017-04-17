package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.HomeworkClass;
import com.homework.param.HomeworkClassParam;
import com.homework.response.ResponseMsg;
import com.homework.service.HomeworkClassService;
import com.homework.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuke
 * @create 2017-04-14 下午 15:23
 **/
@RestController
@RequestMapping(value = "/class",produces="application/json;charset=UTF-8")
public class HomeworkClassController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HomeworkClassService homeworkClassService;

    /**
     * 获取作业设定班级
     * @return
     */
    @RequestMapping(value="", method = RequestMethod.GET)
    public Object getClasses(HomeworkClassParam param) throws Exception{
        logger.info("请求方法getClasses参数---->" + JsonUtil.beanToJson(param));
        ResponseMsg msg = new ResponseMsg();
        Page<HomeworkClass> page = homeworkClassService.selectList(param);
        msg.setData(JsonUtil.beanToJson(page));
        logger.info("请求方法getClasses返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }
}
