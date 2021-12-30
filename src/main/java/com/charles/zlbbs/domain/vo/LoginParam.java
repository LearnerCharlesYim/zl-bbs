package com.charles.zlbbs.domain.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Data
public class LoginParam {

    @Email
    private String email;

    @Length(min = 6,max = 20,message = "密码长度须在6-20字符之间")
    private String password;

    private Integer rememberMe;
}
