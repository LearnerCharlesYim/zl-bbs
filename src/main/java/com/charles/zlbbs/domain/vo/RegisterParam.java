package com.charles.zlbbs.domain.vo;

import com.charles.zlbbs.domain.anno.EmailRequired;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegisterParam {

    @Email(message = "请输入正确邮箱格式")
    @EmailRequired
    private String email;

    @Length(min = 6, max = 6, message = "请输入正确格式邮箱验证码")
    private String emailCaptcha;

    @Length(min = 3, max = 20, message = "用户名长度须在3-20字符之间")
    private String username;

    @Length(min = 6, max = 20, message = "密码长度须在6-20字符之间")
    private String password;

    @Length(min = 6, max = 20, message = "密码长度须在6-20字符之间")
    private String repeatPassword;

    @Length(min = 4, max = 4, message = "请输入正确格式图形验证码")
    private String graphCaptcha;

    @NotBlank(message = "email-uuid缺失")
    private String emailUuid;

    @NotBlank(message = "graph-uuid缺失")
    private String graphUuid;

}
