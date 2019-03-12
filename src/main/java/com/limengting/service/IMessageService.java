package com.limengting.service;

import com.limengting.model.Message;

import java.util.List;
import java.util.Map;

public interface IMessageService {

    /**
     * 获得消息列表
     * @param sessionUid
     * @return
     */
    public Map<String, List<Message>> listMessageByUid(Integer sessionUid);
}
