package com.charles.zlbbs.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.charles.zlbbs.domain.dto.R;
import com.charles.zlbbs.domain.entity.User;
import com.charles.zlbbs.domain.vo.SettingParam;
import com.charles.zlbbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SettingController {

    @Value("${qiniu.url}")
    private String url;

    @Autowired
    private UserService userService;

    @GetMapping("/setting")
    public String setting(Model model) {
        String loginId = (String) StpUtil.getLoginId();
        User user = userService.findById(loginId);
        String avatar = user.getAvatar();
        user.setAvatar(url + avatar);
        model.addAttribute("user", user);
        return "front/setting";
    }

    @ResponseBody
    @PostMapping("/profile/edit")
    public R edit(@Validated SettingParam settingParam) {
        userService.update(settingParam);
        return R.ok();
    }
}
