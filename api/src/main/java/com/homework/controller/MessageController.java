package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Message;
import com.homework.param.MessageParam;
import com.homework.response.ResponseMsg;
import com.homework.service.MessageService;
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
 * @create 2017-04-14 下午 16:48
 **/
@Api(value="Message-api", description="消息控制器")
@RestController
@RequestMapping(value = "/message",produces="application/json;charset=UTF-8")
public class MessageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @ApiOperation(value="发送消息" ,response=ResponseMsg.class)
    @RequestMapping(value="", method = RequestMethod.POST)
    public Object postMessage(@ApiParam("消息") @RequestBody Message message) throws Exception {
        ResponseMsg msg = new ResponseMsg();
        Integer count = messageService.insertMessage(message);
        msg.setData(JsonUtil.beanToJson(count));
        return msg;
    }
    @ApiOperation(value="根据id获取消息" ,response=ResponseMsg.class)
    @RequestMapping(value="", method = RequestMethod.GET)
    public Object getMessage(@ApiParam("消息id") Long id) throws Exception {
        ResponseMsg msg = new ResponseMsg();
        Message message = messageService.selectMessage(id);
        msg.setData(JsonUtil.beanToJson(message));
        return msg;
    }
    @ApiOperation(value="根据消息参数获取消息列表",response=ResponseMsg.class)
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public Object getMessageList(@ApiParam("消息参数") @RequestBody MessageParam param) throws Exception {
        logger.info("请求方法getMessageList参数---->" + JsonUtil.beanToJson(param));
        if(param.getStudentId() == null && UserUtil.getUser().getRole().equals(UserUtil.STUDENT)) {   //当前登录人是学生
            param.setStudentId(UserUtil.getUser().getId());
        }
        if(param.getTeacherId() == null && UserUtil.getUser().getRole().equals(UserUtil.TEACHER)) {   //当前登录人是老师
            param.setTeacherId(UserUtil.getUser().getId());
        }
        ResponseMsg msg = new ResponseMsg();
        Page<Message> messages = messageService.selectMessageList(param);
        msg.setData(messages);
        logger.info("请求方法getMessageList返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }
    @ApiOperation(value="根据消息id读取消息",response=ResponseMsg.class)
    @RequestMapping(value="/read", method = RequestMethod.POST)
    public Object readMsg(@ApiParam("消息id") @RequestBody Long id) throws Exception {
        logger.info("请求方法readMsg参数---->" + id);
        ResponseMsg msg = new ResponseMsg();
        int count = messageService.updateStatus(id);
        msg.setData(count);
        logger.info("请求方法readMsg返回---->" + JsonUtil.beanToJson(msg));
        return msg;
    }

//    public static void main(String[] args) throws Exception{
////        Message message = new Message();
////        message.setContent("好消息");
////        message.setIsRead(0);
////        message.setStudentId(54545L);
////        message.setTeacherId(4444L);
////        message.setType(1);
//        System.out.println(JsonUtil.beanToJson(43434L));
//    }
//
}
