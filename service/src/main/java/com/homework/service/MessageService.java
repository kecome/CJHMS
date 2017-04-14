package com.homework.service;

import com.homework.data.Page;
import com.homework.mapper.MessageMapper;
import com.homework.model.Message;
import com.homework.param.MessageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xuke
 * @create 2017-04-14 下午 17:13
 **/
@Transactional
@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public Message selectMessage(Long id) {
        return messageMapper.selectMessage(id);
    }

    public Page<Message> selectMessageList(MessageParam param) {
        Page<Message> page = new Page<>();
        List<Message> list = messageMapper.selectMessageList(param);
        page.setItems(list);
        if(param.getIsPage()) {
            page.setTotal(messageMapper.count(param));
            return page;
        }
        page.setTotal(list.size());
        return page;
    }

    public int updateStatus(Long id) {
         return messageMapper.updateStatus(id);
    }

    public int insertMessage(Message message) {
        return messageMapper.insertMessage(message);
    }
}
