package com.charles.zlbbs.service;

import cn.dev33.satoken.stp.StpUtil;
import com.charles.zlbbs.domain.entity.User;
import com.charles.zlbbs.domain.enu.ResultCode;
import com.charles.zlbbs.domain.vo.SettingParam;
import com.charles.zlbbs.exception.BizException;
import com.charles.zlbbs.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(String loginId) {
        Optional<User> optionalUser = userRepository.findById(loginId);
        if (!optionalUser.isPresent()) {
            throw new BizException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        return optionalUser.get();
    }

    public void update(SettingParam settingParam) {
        String id = (String) StpUtil.getLoginId();
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new BizException(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
        User user = optionalUser.get();
        BeanUtils.copyProperties(settingParam,user);
        userRepository.save(user);
    }
}
