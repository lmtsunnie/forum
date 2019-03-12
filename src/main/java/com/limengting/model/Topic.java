package com.limengting.model;

import lombok.Data;

@Data
public class Topic {

    private Integer tid;
    //名称
    private String name;
    //描述
    private String content;
    //图片
    private String image;

    public Topic() {}

    public Topic(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
