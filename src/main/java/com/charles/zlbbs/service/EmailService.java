package com.charles.zlbbs.service;

import com.charles.zlbbs.utils.RandomCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailPath;

    @Async
    public void sendSimpleMailMessage(String mailPath,String uuid){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendMailPath);
        message.setTo(mailPath);
        String code = RandomCaptcha.getItemID(6);
        redisTemplate.boundValueOps("captcha:email-captcha:"+uuid).set(code,30, TimeUnit.MINUTES);
        message.setText("验证码为:"+code);
        message.setSubject("知了论坛验证码");
        javaMailSender.send(message);
        log.info("邮件发送完成,{}",code);
    }
}
