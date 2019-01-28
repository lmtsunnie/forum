package com.limengting.controller;

import com.alibaba.fastjson.JSON;
import com.limengting.model.Topic;
import com.limengting.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;


@Controller
@RequestMapping("/")
public class TopicController {

    @Autowired
    private ITopicService topicService;

    private final static Logger logger = Logger.getLogger(TopicController.class.getName());

    /**
     * 【话题】
     * 内容不完善
     * @param model
     * @return
     */
    @RequestMapping("/listTopic.do")
    public String listTopic(Model model) {
        List<Topic> topicList = topicService.listTopic();
        model.addAttribute("topicList", topicList);
        return "topic";
    }

    /**
     * 【校园】【北理人】
     *
     * @param model
     * @return
     */
    @RequestMapping("/listImage.do")
    public String listImage(Model model) {
        logger.info("model:" + JSON.toJSON(model) + "; 调用/listImage.do");
        List<String> imageList = topicService.listImage();
        model.addAttribute("imageList", imageList);
        return "image";
    }
}





