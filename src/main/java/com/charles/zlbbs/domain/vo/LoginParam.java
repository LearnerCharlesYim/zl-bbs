package com.charles.zlbbs.domain.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginParam {

    @NotBlank(message = "邮箱不能为空！")
    @Email(message = "请输入正确邮箱格式！")
    private String email;

    @NotBlank(message = "密码不能为空！")
    private String password;

    private Integer rememberMe;
}
