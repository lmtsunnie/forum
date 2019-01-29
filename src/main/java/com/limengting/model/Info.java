package com.limengting.model;

import lombok.Data;

import java.util.Date;

@Data
public class Info {

    private Integer iid;
    private String requestUrl;
    private String contextPath;
    private String remoteAddr;
    private Date access_time;

    public Info() {
    }

    @Override
    public String toString() {
        return "Info{" +
                "iid=" + iid +
                ", requestUrl='" + requestUrl + '\'' +
                ", contextPath='" + contextPath + '\'' +
                ", remoteAddr='" + remoteAddr + '\'' +
                ", access_time='" + access_time + '\'' +
                '}';
    }
}
