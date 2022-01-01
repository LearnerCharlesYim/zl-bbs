package com.charles.zlbbs.domain.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostParam {

    @Length(min = 3, max = 200, message = "帖子标题字符3-200之间")
    private String title;

    @NotNull(message = "请输入板块id")
    private Integer boardId;

    @NotBlank(message = "帖子内容不能为空")
    private String content;
}
