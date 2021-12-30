package com.charles.zlbbs.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.charles.zlbbs.domain.entity.User;
import com.charles.zlbbs.domain.enu.ResultCode;
import com.charles.zlbbs.domain.vo.LoginParam;
import com.charles.zlbbs.domain.vo.RegisterParam;
import com.charles.zlbbs.exception.BizException;
import com.charles.zlbbs.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Value("${password.key}")
    private String key;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    public void register(RegisterParam registerParam) {
        checkEmailCaptcha(registerParam.getEmailCaptcha(), registerParam.getEmailUuid());
        checkGraphCaptcha(registerParam.getGraphCaptcha(), registerParam.getGraphUuid());
        String password = passwordHandler(registerParam.getPassword(), registerParam.getRepeatPassword());
        User user = new User();
        BeanUtils.copyProperties(registerParam, user);
        user.setPassword(password);
        userRepository.save(user);
        redisTemplate.delete("captcha:email-captcha:" + registerParam.getEmailUuid());
        redisTemplate.delete("captcha:graph-captcha:" + registerParam.getGraphUuid());

    }

    private String passwordHandler(String password, String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new BizException(ResultCode.INCONSISTENT_PASSWORD);
        }
        return SaSecureUtil.aesEncrypt(key, password);
    }

    private void checkGraphCaptcha(String graphCaptcha, String graphUuid) {
        String code = redisTemplate.boundValueOps("captcha:graph-captcha:" + graphUuid).get();
        if (code == null) {
            throw new BizException(ResultCode.GRAPH_CAPTCHA_EXPIRED);
        }
        if (!code.equalsIgnoreCase(graphCaptcha)) {
            throw new BizException(ResultCode.GRAPH_CAPTCHA_ERROR);
        }
    }

    private void checkEmailCaptcha(String emailCaptcha, String emailUuid) {
        String code = redisTemplate.boundValueOps("captcha:email-captcha:" + emailUuid).get();
        if (code == null) {
            throw new BizException(ResultCode.EMAIL_EXPIRED);
        }
        if (!code.equalsIgnoreCase(emailCaptcha)) {
            throw new BizException(ResultCode.EMAIL_ERROR);
        }
    }

    public void login(LoginParam loginParam) {
        User user = userRepository.findByEmail(loginParam.getEmail());
        if (user == null) {
            throw new BizException(ResultCode.USER_CREDENTIALS_ERROR);
        }
        String password = SaSecureUtil.aesDecrypt(key, user.getPassword());
        if (!password.equals(loginParam.getPassword())) {
            throw new BizException(ResultCode.USER_CREDENTIALS_ERROR);
        }
        if(loginParam.getRememberMe() == 1){
            StpUtil.login(user.getId(),new SaLoginModel()
                    .setIsLastingCookie(true)
                    .setTimeout(60 * 60 * 24 * 7));
        }else {
            StpUtil.login(user.getId(),false);
        }
        StpUtil.getSession().set("loginUser",user.getUsername());
    }
}
