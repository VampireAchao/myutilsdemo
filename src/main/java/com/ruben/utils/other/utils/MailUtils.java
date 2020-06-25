package com.ruben.utils.other.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

public class MailUtils {
    public static void sendMail(String toAddr, String subject, String emailMsg) throws MessagingException {
        //1.创建程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        //设置发送协议
        props.setProperty("mail.transport.protocol", "SMTP");
        //设置发送邮件服务器
        props.setProperty("mail.host", "smtp.163.com");
        //服务器是否要验证用户身份信息
        props.setProperty("mail.smtp.auth", "true");
        //创建验证器
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //设置发送人帐号和密码
                return new PasswordAuthentication("bruser3", "8449559159");
            }
        };
        //获得Session
        Session session = Session.getInstance(props, auth);

        //2.创建一个MimeMessage格式邮件，相当于是邮件内容
        Message message = new MimeMessage(session);
        //设置发送者
        message.setFrom(new InternetAddress("bruser3@163.com"));
        //设置发送方式与接收者
        message.setRecipient(RecipientType.TO, new InternetAddress(toAddr));
        //设置邮件主题
        message.setSubject(subject);
        //设置邮件内容 为text
        //message.setText("这是一封激活邮件，请<a href='#'>点击</a>");
        //设置邮件内容 为html
        message.setContent(emailMsg, "text/html;charset=utf-8");

        //3.创建Transport用于将邮件发送
        Transport.send(message);
    }
}


