package com.charles.zlbbs.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.charles.zlbbs.domain.dto.R;
import com.charles.zlbbs.domain.vo.LoginParam;
import com.charles.zlbbs.domain.vo.RegisterParam;
import com.charles.zlbbs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ResponseBody
    @PostMapping("/register")
    public R register(@Validated RegisterParam registerParam,
                      @CookieValue("graph-captcha-uuid") String graphUuid) {
        registerParam.setGraphUuid(graphUuid);
        loginService.register(registerParam);
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/login")
    public R login(@Validated @RequestBody LoginParam loginParam) {
        loginService.login(loginParam);
        return R.ok();
    }

    @RequestMapping("/logout")
    public String logout() {
        StpUtil.logout();
        return "redirect:/login.html";
    }
}
