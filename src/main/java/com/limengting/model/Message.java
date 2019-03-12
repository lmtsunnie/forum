package com.limengting.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private Integer mid;

    //外键，指向User
    private Integer uid;

    //哪个用户
    private Integer otherId;
    private String otherUsername;
    //什么操作
    private String operation;
    //操作了什么内容
    private Integer postId;
    //帖子，回复，评论
    private String displayedContent;

    private Date MsgTime;

    public Message() {}

    @Override
    public String toString() {
        return "Message{" +
                "mid=" + mid +
                ", uid=" + uid +
                ", otherId=" + otherId +
                ", otherUsername='" + otherUsername + '\'' +
                ", operation='" + operation + '\'' +
                ", postId=" + postId +
                ", displayedContent='" + displayedContent + '\'' +
                ", MsgTime=" + MsgTime +
                '}';
    }
}
