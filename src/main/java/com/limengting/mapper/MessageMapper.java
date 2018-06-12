package com.limengting.mapper;

import com.limengting.model.Message;

import java.util.List;


public interface MessageMapper {

    void insertMessage(Message message);

    List<Message> listMessageByUid(Integer uid);


}
