package com.limengting.serviceimpl;

import com.limengting.async.MailTask;
import com.limengting.mapper.UserMapper;
import com.limengting.model.Info;
import com.limengting.model.User;
import com.limengting.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取自己/他人主页
     * @param sessionUid
     * @param uid
     * @return
     */
    @Override
    public User getProfile(int sessionUid, int uid) {
        //如果是浏览别人的主页，则增加主页浏览数
        if (sessionUid != uid) {
            userMapper.updateScanCount(uid);
        }
        //从数据库得到User对象
        User user = userMapper.selectUserByUid(uid);
        //设置获赞数，关注数，粉丝数
        Jedis jedis = jedisPool.getResource();
        // scard 获取set的成员个数
        user.setFollowCount((int) (long) jedis.scard(uid + ":follow"));
        user.setFollowerCount((int) (long) jedis.scard(uid + ":fans"));
        // HGET key field 获取存储在哈希表中指定字段的值
        String likeCount = jedis.hget("vote", uid + "");
        if (likeCount == null) {
            user.setLikeCount(0);
        } else {
            user.setLikeCount(Integer.valueOf(likeCount));
        }

        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
        return user;
    }

    @Override
    public User getEditInfo(int uid) {
        return userMapper.selectEditInfo(uid);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void record(StringBuffer requestURL, String contextPath, String remoteAddr) {
        Info info = new Info();
        info.setRequestUrl(requestURL.toString());
        info.setContextPath(contextPath);
        info.setRemoteAddr(remoteAddr);
        info.setAccess_time(new Date());
        userMapper.insertInfo(info);
    }

    @Override
    public List<User> listUserByTime() {
        return userMapper.listUserByJoinTime();
    }

    @Override
    public List<User> listUserByHot() {
        return userMapper.listUserByPostCount();
    }

    @Override
    public void updateHeadUrl(int uid, String headUrl) {
        userMapper.updateHeadUrl(uid, headUrl);
    }

    @Override
    public void unfollow(int sessionUid, int uid) {
        Jedis jedis = jedisPool.getResource();
        // 以 MULTI 开始一个事务， 然后将多个命令入队到事务中， 最后由 EXEC 命令触发事务， 一并执行事务中的所有命令
        Transaction transaction = jedis.multi();
        // srem key member1 [member2] 移除集合中一个或多个成员
        transaction.srem(sessionUid + ":follow", String.valueOf(uid));
        transaction.srem(uid + ":fans", String.valueOf(sessionUid));
        transaction.exec();

        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    @Override
    public void follow(int sessionUid, int uid) {
        Jedis jedis = jedisPool.getResource();
        Transaction transaction = jedis.multi();
        // SADD key member1 [member2] 向集合添加一个或多个成员
        // sessionUid follow的人中加入uid
        transaction.sadd(sessionUid + ":follow", String.valueOf(uid));
        // uid的fans的集合中加入sessionUid
        transaction.sadd(uid + ":fans", String.valueOf(sessionUid));
        transaction.exec();
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    @Override
    public boolean getFollowStatus(int sessionUid, int uid) {
        Jedis jedis = jedisPool.getResource();
        // SISMEMBER key member 判断 member 元素是否是集合 key 的成员
        boolean following = jedis.sismember(sessionUid + ":follow", String.valueOf(uid));
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
        return following;
    }

    @Override
    public String updatePassword(String password, String newpassword, String repassword, int sessionUid) {
        String oldPassword = userMapper.selectPasswordByUid(sessionUid);
        if (!oldPassword.equals(password)) {
            return "原密码输入错误";
        }

        if (newpassword.length() < 6 || newpassword.length() > 20) {
            return "新密码长度要在6~20之间";
        }

        if (!newpassword.equals(repassword)) {
            return "新密码两次输入不一致";
        }

        userMapper.updatePassword(newpassword, sessionUid);
        return "ok";
    }

    //发送忘记密码确认邮件
    @Override
    public void forgetPassword(String email) {
        String verifyCode = userMapper.selectVerifyCode(email);
        System.out.println("verifyCode:" + verifyCode);
        //发送邮件
        taskExecutor.execute(new MailTask(verifyCode, email, javaMailSender, 2));
    }

    @Override
    public void verifyForgetPassword(String code) {
        System.out.println("更新前：" + code);
        userMapper.updatePasswordByActivateCode(code);
        System.out.println("更新后：" + code);
    }
}
