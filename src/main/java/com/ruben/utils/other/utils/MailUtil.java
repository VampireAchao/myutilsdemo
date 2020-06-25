package com.ruben.utils.other.utils;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;

public class MailUtil {

    public static void sendMail(String toAddr, String subject, String content) throws Exception {
        //1.创建程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        //设置邮件发送服务器地址
        props.put("mail.smtp.host", "smtp.163.com");
        //服务器是否要验证用户身份信息
        props.put("mail.smtp.auth", "true");
        //获得Session
        Session session = Session.getInstance(props);
        session.setDebug(true); //代表启用debug模式，可在控制台输出smtp协议应答过程*

        //2.创建一个MimeMessage格式邮件
        MimeMessage message = new MimeMessage(session);
        //设置发送者
        Address fromAddress = new InternetAddress("bruser3@163.com"); //邮件地址
        message.setFrom(fromAddress); //设置发送邮件地址
        //设置接收者
        Address toAddress = new InternetAddress(toAddr); //邮件地址
        message.setRecipient(RecipientType.TO, toAddress); //设置接收者的地址
        //设置邮件的主题
        message.setSubject(subject);
        //设置邮件内容 为text
        message.setText(content);
        //设置邮件内容 为html
        //message.setContent(content, "text/html;charset=utf-8");
        //保存邮件
        message.saveChanges();

        //3.取得发送邮件的transport
        Transport transport = session.getTransport("smtp");
        //连接到服务器上
        transport.connect("smtp.163.com", "bruser3", "8449559159");
        //transport发送
        transport.sendMessage(message, message.getAllRecipients());
        //关闭通道
        transport.close();
    }
}
