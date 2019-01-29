package com.limengting.model;

import lombok.Data;

@Data
public class Post {
    private Integer pid;
    //标题和内容
    private String title;
    private String content;
    //两个时间
    private String publishTime;
    private String replyTime;

    //三个数量
    private Integer replyCount;
    private Integer likeCount;
    private Integer scanCount;

    //两个外键
    private User user;
    private Topic topic;

    public Post() {}

    public Post(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Post{" +
                "pid=" + pid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", replyTime='" + replyTime + '\'' +
                ", replyCount=" + replyCount +
                ", likeCount=" + likeCount +
                ", scanCount=" + scanCount +
                ", user=" + user +
                ", topic=" + topic +
                '}';
    }
}
