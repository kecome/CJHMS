package com.homework.controller;

import com.homework.data.Page;
import com.homework.model.Message;
import com.homework.param.MessageParam;
import com.homework.response.ResponseMsg;
import com.homework.service.MessageService;
import com.homework.util.JsonUtil;
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
@RestController
@RequestMapping(value = "/message",produces="application/json;charset=UTF-8")
public class MessageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @RequestMapping(value="", method = RequestMethod.POST)
    public Object postMessage(@RequestBody Message message) throws Exception {
        ResponseMsg msg = new ResponseMsg();
        Integer count = messageService.insertMessage(message);
        msg.setData(JsonUtil.beanToJson(count));
        return msg;
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public Object getMessage(Long id) throws Exception {
        ResponseMsg msg = new ResponseMsg();
        Message message = messageService.selectMessage(id);
        msg.setData(JsonUtil.beanToJson(message));
        return msg;
    }

    @RequestMapping(value="/list", method = RequestMethod.POST)
    public Object getMessageList(@RequestBody MessageParam param) throws Exception {
        ResponseMsg msg = new ResponseMsg();
        Page<Message> messages = messageService.selectMessageList(param);
        msg.setData(JsonUtil.beanToJson(messages));
        return msg;
    }

//    public static void main(String[] args) throws Exception{
//        Message message = new Message();
//        message.setContent("好消息");
//        message.setIsRead(0);
//        message.setStudentId(54545L);
//        message.setTeacherId(4444L);
//        message.setType(1);
//        System.out.println(JsonUtil.beanToJson(message));
//    }

}
