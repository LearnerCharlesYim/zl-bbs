package com.charles.zlbbs.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
public class CaptchaController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 40, 4, 3);
        //图形验证码写出，可以写出到文件，也可以写出到流
        String code = captcha.getCode();
        String uuid = IdUtil.simpleUUID();
        redisTemplate.boundValueOps("captcha:graph-captcha:"+uuid).set(code,10, TimeUnit.MINUTES);
        response.addCookie(new Cookie("graph-captcha-uuid", uuid));
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        captcha.write(response.getOutputStream());
        response.getOutputStream().close();
        log.info("生成的验证码:{}", code);
    }
}
