package com.limengting.model;

import lombok.Data;

import java.util.List;

@Data
public class Reply {

    private Integer rid;
    //回帖内容
    private String content;
    //两个外键，指向Post和User
    private Post post;
    private User user;

    //回复时间
    private String replyTime;

    //存储楼中楼的集合
    private List<Comment> commentList;

    public Reply() {}

    public Reply(Integer rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "rid=" + rid +
                ", content='" + content + '\'' +
                ", post=" + post +
                ", user=" + user +
                ", replyTime='" + replyTime + '\'' +
                ", commentList=" + commentList +
                '}';
    }
}
