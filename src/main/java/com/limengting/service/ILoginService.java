package com.limengting.service;

import com.limengting.model.User;
import java.util.Map;

public interface ILoginService {

    /**
     * 注册
     * @param user
     * @param repassword
     * @return
     */
    String register(User user, String repassword);

    /**
     * 登录
     * @param user
     * @return
     */
    Map<String, Object> login(User user);

    /**
     * 激活
     * @param activateCode
     */
    void activate(String activateCode);
}
