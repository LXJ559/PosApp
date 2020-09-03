package com.example.demo.job;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SendMailJob {
    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Async
    public void sendVCode(int vCode, String email){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("验证码");
        simpleMailMessage.setText("您好，你的验证码为"+vCode+",5min内有效。");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("1625464358@qq.com");
        javaMailSender.send(simpleMailMessage);
    }
}
