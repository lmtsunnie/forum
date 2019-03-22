package com.limengting.service;

import com.limengting.model.User;

import java.util.List;


public interface IUserService {

    /**
     * 获取自己/他人主页
     *
     * @param sessionUid
     * @param uid
     * @return
     */
    User getProfile(int sessionUid, int uid);

    /**
     * @param uid
     * @return
     */
    User getEditInfo(int uid);

    /**
     * @param user
     */
    void updateUser(User user);

    /**
     * @param requestURL
     * @param contextPath
     * @param remoteAddr
     */
    void record(StringBuffer requestURL, String contextPath, String remoteAddr);

    /**
     * @return
     */
    List<User> listUserByJoinTime();

    /**
     * @return
     */
    List<User> listUserByPostCount();

    /**
     * 更新头像
     * @param uid
     * @param headUrl
     */
    void updateHeadUrl(int uid, String headUrl);

    /**
     * sessionUid unfollow uid
     * @param sessionUid
     * @param uid
     */
    void unfollow(int sessionUid, int uid);

    /**
     * sessionUid follow uid
     * @param sessionUid
     * @param uid
     */
    void follow(int sessionUid, int uid);

    /**
     * sessionUid是否follow uid
     * @param sessionUid
     * @param uid
     * @return
     */
    boolean getFollowStatus(int sessionUid, int uid);

    /**
     * @param password
     * @param newpassword
     * @param repassword
     * @param sessionUid
     * @return
     */
    String updatePassword(String password, String newpassword, String repassword, int sessionUid);

    /**
     * 发送忘记密码确认邮件
     *
     * @param email
     */
    void forgetPassword(String email);

    /**
     * @param code
     */
    void verifyForgetPassword(String code);
}

