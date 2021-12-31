package com.charles.zlbbs.domain.anno;

import com.charles.zlbbs.validator.EmailRequiredValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailRequiredValidator.class)
public @interface EmailRequired {

    boolean required() default true;

    // 这个地方修改错误提示字符，其他地方不要修改
    String message() default "邮箱已注册";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
