package com.limengting.controller;

import com.limengting.mapper.UserMapper;
import com.limengting.model.Post;
import com.limengting.model.User;
import com.limengting.service.IPostService;
import com.limengting.service.IQiniuService;
import com.limengting.service.IUserService;
import com.limengting.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IQiniuService qiniuService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 【头像】【个人主页】
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/toMyProfile.do")
    public String toMyProfile(HttpSession session, Model model) {
        int sessionUid = (int) session.getAttribute("uid");
        User user = userService.getProfile(sessionUid, sessionUid);
        List<Post> postList = postService.getPostList(sessionUid);
        model.addAttribute("user", user);
        model.addAttribute("postList", postList);
        return "myProfile";
    }


    /**
     * 查看别人的主页
     *
     * @param uid
     * @param model
     * @return
     */
    @RequestMapping("/toProfile.do")
    public String toProfile(int uid, Model model, HttpSession session) {
        //如果是自己的页面，直接跳转到本人个人主页
        int sessionUid = (int) session.getAttribute("uid");
        if (sessionUid == uid) {
            return "redirect:toMyProfile.do";
        }
        //判断是否关注当前用户
        boolean following = userService.getFollowStatus(sessionUid, uid);
        //获得用户信息
        User user = userService.getProfile(sessionUid, uid);
        //获得发帖列表
        List<Post> postList = postService.getPostList(uid);
        //向model中添加数据
        model.addAttribute("following", following);
        model.addAttribute("user", user);
        model.addAttribute("postList", postList);
        return "profile";
    }


    /**
     * 【编辑信息】
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/toEditProfile.do")
    public String toEditProfile(HttpSession session, Model model) {
        int uid = (int) session.getAttribute("uid");
        User user = userService.getEditInfo(uid);
        model.addAttribute("user", user);
        return "editProfile";
    }

    /**
     * 编辑用户名、签名等基本信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/editProfile.do")
    public String editProfile(User user) {
        System.out.println(user);
        userService.updateUser(user);
        return "redirect:toMyProfile.do";
    }

    /**
     * 修改密码
     *
     * @param password
     * @param newpassword
     * @param repassword
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/updatePassword.do")
    public String updatePassword(String password, String newpassword, String repassword, HttpSession session, Model model) {
        int sessionUid = (int) session.getAttribute("uid");
        String status = userService.updatePassword(password, newpassword, repassword, sessionUid);
        if (status.equals("ok")) {
            return "redirect:logout.do";
        } else {
            model.addAttribute("passwordError", status);
            return "editProfile";
        }
    }

    /**
     * 忘记密码
     *
     * @param email
     * @return
     */
    @RequestMapping("/forgetPassword.do")
    public @ResponseBody String forgetPassword(String email) {
        userService.forgetPassword(email);
        return "";
    }

    /**
     * 忘记密码发送邮件之后
     *
     * @return
     */
    @RequestMapping("/afterForgetPassword.do")
    public String afterForgetPassword(Model model) {
        model.addAttribute("info", "系统已经将新密码发至您的邮箱，请用新密码重新登录");
        return "prompt/promptInfo";
    }

    /**
     * 更新头像???没用
     *
     * @param file
     * @param model
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/updateHeadUrl.do")
    public String updateHeadUrl(MultipartFile file, Model model, HttpSession session) throws IOException {
        // 文件类型限制
        String[] allowedType = {"image/bmp", "image/gif", "image/jpeg", "image/png"};
        boolean allowed = Arrays.asList(allowedType).contains(file.getContentType());
        if (!allowed) {
            model.addAttribute("error3", "图片格式仅限bmp，jpg，png，gif");
            return "editProfile";
        }
        // 图片大小限制
        if (file.getSize() > 3 * 1024 * 1024) {
            model.addAttribute("error3", "图片大小限制在3M以下");
            return "editProfile";
        }
        // 包含原始文件名的字符串
        String filename = file.getOriginalFilename();
        // 提取文件拓展名
        String fileNameExtension = filename.substring(filename.indexOf("."), filename.length());
        // 生成云端的真实文件名
        /*String remoteFileName = UUID.randomUUID().toString() + fileNameExtension;
        qiniuService.upload(file.getBytes(), remoteFileName);*/
        String hash = qiniuService.upload(file.getBytes(), filename);

        //更新数据库中头像URL
        int uid = (int) session.getAttribute("uid");
        String newHeadUrl = Constant.QINIU_IMAGE_URL + hash;
        // 更新session中的headUrl
        session.setAttribute("headUrl", newHeadUrl);
        userService.updateHeadUrl(uid, newHeadUrl);

        return "redirect:toMyProfile.do";
    }

    /**
     * 【关注】
     *
     * @param uid
     * @param session
     * @return
     */
    @RequestMapping("/follow.do")
    public String follow(int uid, HttpSession session) {
        int sessionUid = (int) session.getAttribute("uid");
        userService.follow(sessionUid, uid);
        return "forward:toProfile.do";
    }

    /**
     * 【取消关注】
     *
     * @param uid
     * @param session
     * @return
     */
    @RequestMapping("/unfollow.do")
    public String unfollow(int uid, HttpSession session) {
        int sessionUid = (int) session.getAttribute("uid");
        userService.unfollow(sessionUid, uid);
        return "forward:toProfile.do";
    }

    /**
     * 收到激活邮件后确认将密码修改为"xxx"???没用
     *
     * @param code
     * @return
     */
    @RequestMapping("/verify.do")
    public String verify(String code) {
        userService.verifyForgetPassword(code);
        return "redirect:toLogin.do";
    }
}

