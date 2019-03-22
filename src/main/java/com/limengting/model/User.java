package com.limengting.model;

import lombok.Data;

@Data
public class User {

    private Integer uid;

    //系统属性
    private String email;
    private String password;
    private Integer actived;
    private String activateCode;
    private String joinTime;

    //用户属性
    private String username;
    private String description;
    private String headUrl;
    private String position;
    private String school;
    private String job;

    //附加属性
    private Integer postCount;
    private Integer scanCount;
    private Integer likeCount;
    private Integer followCount;
    private Integer followerCount;

    public User() {}

    public User(Integer uid) {
        this.uid = uid;
    }
}
