package com.limengting.controller;


import com.limengting.model.Message;
import com.limengting.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MessageController {


    @Autowired
    private MessageService messageService;

    /**
     * 【消息】同一个人点赞多次点赞数+1但是会发多条消息，而且无法取消赞???
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/toMessage.do")
    public String toMessage(Model model, HttpSession session) {
        Integer sessionUid = (Integer) session.getAttribute("uid");
        Map<String, List<Message>> map = messageService.listMessageByUid(sessionUid);
        model.addAttribute("map", map);
        System.out.println(map);
        return "message";
    }

}
