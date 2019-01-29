package com.limengting.async;

import com.limengting.common.Constant;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

public class MailTask implements Runnable {

    private String code;
    private String email;
    private JavaMailSender javaMailSender;
    private int operation;

    public MailTask(String code, String email, JavaMailSender javaMailSender, int operation) {
        this.code = code;
        this.email = email;
        this.javaMailSender = javaMailSender;
        this.operation = operation;
    }

    @Override
    public void run() {
        javaMailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                System.out.println("begin to send email...");
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setFrom(Constant.MAIL_FROM);
                mimeMessageHelper.setTo(email);
                mimeMessageHelper.setSubject("一封激活邮件");
                StringBuilder sb = new StringBuilder();
                sb.append("<html><head></head><body>");

                if (operation == 1) {
                    sb.append("这是一封北理人论坛的激活邮件：<br />");
                    sb.append("<a href=" + Constant.DOMAIN_NAME + "activate.do?code=");
                    sb.append(code);
                    sb.append(">点击激活</a>");
                    sb.append("</body>");
                } else {
                    sb.append("是否将您的密码修改为: <br />");
                    sb.append(code.substring(0, 8));
                    sb.append("，<a href=" + Constant.DOMAIN_NAME + "verify.do?code=" + code + ">");
                    sb.append("点击是</a></body>");
                }

                mimeMessageHelper.setText(sb.toString(), true);

                System.out.println("end sending email...");
            }
        });
    }
}








