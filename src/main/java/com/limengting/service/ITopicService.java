package com.limengting.service;

import com.limengting.model.Topic;

import java.util.List;


public interface ITopicService {

    /**
     * 话题广场
     * @return
     */
    public List<Topic> listTopic();
}

