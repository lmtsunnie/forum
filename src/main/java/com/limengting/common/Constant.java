package com.limengting.common;


public class Constant {
    public static final String QINIU_IMAGE_URL = "http://qiniu.limengting.site/";
    public static final String QINIU_ACCESS_KEY = "4Uk12cIO6mqC3QR0DapG349IFjP5bA_bMwIl0mcj";
    public static final String QINIU_SECRET_KEY = "6-R9_xaEnpkR34WZf8HTTsKCJ2o8cnUk1ZJZYlnO";
    public static final String QINIU_BUCKET_NAME = "photos";

    //发送邮件的邮箱，要与application.properties中的一致
    public static final String MAIL_FROM = "lmtsunnie@sina.com";

    //域名
    //public static final String DOMAIN_NAME = "http://192.168.3.8:8080/"; // change to your own IP
    public static final String DOMAIN_NAME = "http://123.207.143.168:9000/"; // change to your own IP

    //三种操作
    public static final int OPERATION_CLICK_LIKE = 1;
    public static final int OPERATION_REPLY = 2;
    public static final int OPERATION_COMMENT = 3;

}
