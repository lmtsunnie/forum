package com.limengting.serviceimpl;

import com.limengting.common.Util;
import com.limengting.mapper.MessageMapper;
import com.limengting.model.Message;
import com.limengting.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    //获得消息列表
    @Override
    public Map<String, List<Message>> listMessageByUid(Integer sessionUid) {
        List<Message> messageList = messageMapper.listMessageByUid(sessionUid);
        Map<String, List<Message>> map = new HashMap<>();
        for(Message message : messageList){
            String time = Util.formatDate(message.getMsgTime()).substring(0,11);
            if(map.get(time)==null){
                map.put(time,new LinkedList<Message>());
                map.get(time).add(message);
            }else{
                map.get(time).add(message);
            }
        }
        return map;
    }
}
