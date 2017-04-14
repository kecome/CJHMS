package com.homework.mapper;

import com.homework.model.Message;
import com.homework.param.MessageParam;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xuke on 2017/4/14 0014.
 */
public interface MessageMapper {
    @Select("select * from message where id = #{id}")
    Message selectMessage(Long id);
    List<Message> selectMessageList(MessageParam param);
    Long count(MessageParam param);
    int updateStatus(Long id);
    int insertMessage(Message message);
}
