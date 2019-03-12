package com.limengting.serviceimpl;

import com.limengting.mapper.TopicMapper;
import com.limengting.model.Topic;
import com.limengting.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicServiceImpl implements ITopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Topic> listTopic() {
        return topicMapper.listTopic();
    }
}

