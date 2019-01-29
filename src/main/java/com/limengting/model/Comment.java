package com.limengting.model;

import lombok.Data;

@Data
public class Comment {

    private Integer cid;

    //评论内容
    private String content;
    //两个外键，指向Reply和User
    private Reply reply;
    private User user;

    //评论时间
    private String commentTime;

    public Comment() {}

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", content='" + content + '\'' +
                ", reply=" + reply +
                ", user=" + user +
                ", commentTime='" + commentTime + '\'' +
                '}';
    }
}
