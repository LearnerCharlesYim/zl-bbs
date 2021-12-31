package com.charles.zlbbs.domain.anno;

import com.charles.zlbbs.validator.ImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageValidator.class)
public @interface ImageRequired {

    boolean required() default true;

    // 这个地方修改错误提示字符，其他地方不要修改
    String message() default "上传图片格式错误，且图片大小不能超过2MB";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
