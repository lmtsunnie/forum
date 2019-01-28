package com.limengting.controller;

import com.limengting.model.*;
import com.limengting.service.IPostService;
import com.limengting.service.IReplyService;
import com.limengting.service.ITopicService;
import com.limengting.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class PostController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @Autowired
    private ITopicService topicService;

    @Autowired
    private IReplyService replyService;


    /**
     * 【我要发布】
     *
     * @param model
     * @return
     */
    @RequestMapping("/toPublish.do")
    public String toPublish(Model model) {
        List<Topic> topicList = topicService.listTopic();
        model.addAttribute("topicList", topicList);
        return "publish";
    }

    /**
     * 【发布】
     * @param post
     * @return
     */
    @RequestMapping("/publishPost.do")
    public String publishPost(Post post) {
        int id = postService.publishPost(post);
        return "redirect:toPost.do?pid=" + id;
    }


    /**
     * 帖子第几页，按时间顺序
     * @param curPage
     * @param model
     * @return
     */
    @RequestMapping("/listPostByTime.do")
    public String listPostByTime(int curPage, Model model) {
        PageBean<Post> pageBean = postService.listPostByTime(curPage);
        List<User> userList = userService.listUserByTime();
        List<User> hotUserList = userService.listUserByHot();
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("userList", userList);
        model.addAttribute("hotUserList", hotUserList);
        return "index";
    }

    /**
     * 点某一篇帖子，去看详情
     * @param pid
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/toPost.do")
    public String toPost(int pid, Model model, HttpSession session) {
        Integer sessionUid = (Integer) session.getAttribute("uid");
        //获取帖子信息
        Post post = postService.getPostByPid(pid);
        //获取评论信息
        List<Reply> replyList = replyService.listReply(pid);

        //判断用户是否已经点赞

        boolean liked = false;
        if (sessionUid != null) {
            liked = postService.getLikeStatus(pid, sessionUid);
        }
        //向模型中添加数据
        model.addAttribute("post", post);
        model.addAttribute("replyList", replyList);
        model.addAttribute("liked", liked);
        return "post";
    }

    /**
     * 给帖子点赞（异步）???不能取消赞
     * @param pid
     * @param session
     * @return
     */
    @RequestMapping(value = "/ajaxClickLike.do", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String ajaxClickLike(int pid, HttpSession session) {
        int sessionUid = (int) session.getAttribute("uid");
        return postService.clickLike(pid, sessionUid);
    }
}
