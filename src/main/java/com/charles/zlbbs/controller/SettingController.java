package com.charles.zlbbs.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.charles.zlbbs.domain.entity.User;
import com.charles.zlbbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingController {

    @Autowired
    private UserService userService;

    @GetMapping("/setting")
    public String setting(Model model) {
        String loginId = (String) StpUtil.getLoginId();
        User user = userService.findById(loginId);
        model.addAttribute("user", user);
        return "front/setting";
    }
}
