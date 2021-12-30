package com.charles.zlbbs.controller;

import cn.hutool.core.util.IdUtil;
import com.charles.zlbbs.domain.dto.R;
import com.charles.zlbbs.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/email/captcha")
    public R sendCaptcha(String email) {
        String uuid = IdUtil.simpleUUID();
        emailService.sendSimpleMailMessage(email, uuid);
        Map<String,String> map = new HashMap<>();
        map.put("uuid",uuid);
        return R.ok(map);
    }
}
