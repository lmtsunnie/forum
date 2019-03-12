package com.limengting.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Util {

    /**
     * 日期格式化
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 随机生成激活码
     * @return
     */
    public static String createActivateCode() {
        return new Date().getTime() + UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(createActivateCode().length());
    }
}
