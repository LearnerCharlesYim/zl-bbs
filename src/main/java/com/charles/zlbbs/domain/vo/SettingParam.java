package com.charles.zlbbs.domain.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SettingParam {

    private String avatar;

    @Length(max = 200,message = "个性签名字符不超过200")
    private String signature;
}
