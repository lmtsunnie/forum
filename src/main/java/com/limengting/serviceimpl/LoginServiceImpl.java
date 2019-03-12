package com.limengting.serviceimpl;

import com.limengting.async.MailTask;
import com.limengting.common.Constant;
import com.limengting.mapper.UserMapper;
import com.limengting.model.User;
import com.limengting.service.ILoginService;
import com.limengting.common.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String register(User user, String repassword) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
        Matcher matcher = pattern.matcher(user.getEmail());
        // 校验邮箱格式
        if (!matcher.matches()) {
            return "邮箱格式不合法，请重新输入邮箱";
        }

        // 校验密码长度
        pattern = Pattern.compile("^\\w{6,20}$");
        matcher = pattern.matcher(user.getPassword());
        if (!matcher.matches()) {
            return "密码长度要在6到20之间，请重新输入密码";
        }

        // 检查两次密码是否一致
        if (!user.getPassword().equals(repassword)) {
            return "两次密码输入不一致，请重新输入";
        }

        // 检查邮箱是否被注册
        int emailCount = userMapper.selectEmailCount(user.getEmail());
        if (emailCount > 0) {
            return "该邮箱已经被注册";
        }

        // 构造user，设置未激活
        user.setActived(0);
        // 随机生成激活码
        String activateCode = Util.createActivateCode();
        user.setActivateCode(activateCode);
        // 加入的时间
        user.setJoinTime(Util.formatDate(new Date()));
        // 名字
        user.setUsername("婷婷" + new Random().nextInt(10000) + "号");
        // 头像
        user.setHeadUrl(Constant.QINIU_IMAGE_URL + "figure12.jpg");

        // 异步执行：发送激活邮件
        taskExecutor.execute(new MailTask(activateCode, user.getEmail(), javaMailSender, 1));

        //向数据库插入记录
        userMapper.insertUser(user);

        return "ok";
    }

    @Override
    public Map<String, Object> login(User user) {
        Map<String, Object> map = new HashMap<>();
        // email & password -> uid
        Integer uid = userMapper.selectUidByEmailAndPassword(user);
        if (uid == null) {
            map.put("status", "no");
            map.put("error", "用户名或密码错误");
            return map;
        }

        int checkActived = userMapper.selectActived(user);
        if (checkActived == 0) {
            map.put("status", "no");
            map.put("error", "该账号还未激活，请前往邮箱激活");
            return map;
        }

        String headUrl = userMapper.selectHeadUrl(uid);

        map.put("status", "yes");
        map.put("uid", uid);
        map.put("headUrl", headUrl);
        return map;
    }

    @Override
    public void activate(String activateCode) {
        userMapper.updateActived(activateCode);
    }
}
