package com.limengting.controller;

import com.limengting.model.User;
import com.limengting.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    private final static Logger logger = Logger.getLogger(LoginController.class.getName());

    /**
     * 登录和注册的页面
     *
     * @return
     */
    @RequestMapping("/toLogin.do")
    public String toLogin() {
        return "login";
    }

    /**
     * 【立即注册】按钮
     *
     * @param user
     * @param repassword
     * @param model
     * @return
     */
    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public String register(User user, String repassword, Model model) {
        String result = loginService.register(user, repassword);
        if (result.equals("ok")) {
            model.addAttribute("info", "系统已经向您的邮箱发送了一封激活邮件，验证后就可以登录了。");
            return "prompt/promptInfo";
        } else {
            model.addAttribute("register", "yes");
            model.addAttribute("email", user.getEmail());
            model.addAttribute("error", result);
            return "login";
        }
    }


    /**
     * 【立即登录】按钮
     *
     * @param user
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session) {
        Map<String, Object> map = loginService.login(user);
        if (map.get("status").equals("yes")) {
            session.setAttribute("uid", map.get("uid"));
            session.setAttribute("headUrl", map.get("headUrl"));
            return "redirect:toMyProfile.do";
        } else {
            model.addAttribute("email", user.getEmail());
            model.addAttribute("error", map.get("error"));
            return "login";
        }
    }

    /**
     * 激活
     * ???加需求：再次点击链接显示已经过期
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "/activate.do")
    public String activate(String code, Model model) {
        loginService.activate(code);

        model.addAttribute("info", "您的账户已经激活成功，请点击右上角登录");
        return "prompt/promptInfo";
    }


    /**
     * 【退出登录】
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("uid");
        return "redirect:post.do";
    }
}
