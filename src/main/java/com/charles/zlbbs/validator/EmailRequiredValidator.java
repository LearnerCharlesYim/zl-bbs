package com.charles.zlbbs.validator;

import com.charles.zlbbs.domain.anno.RequiredEmail;
import com.charles.zlbbs.domain.entity.User;
import com.charles.zlbbs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailRequiredValidator implements ConstraintValidator<RequiredEmail, String> {

    @Autowired
    private UserRepository userRepository;

    private boolean required = false;

    @Override
    public void initialize(RequiredEmail constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            User user = userRepository.findByEmail(s);
            return user == null;
        } else {
            if (StringUtils.hasText(s)) {
                User user = userRepository.findByEmail(s);
                return user != null;
            } else {
                return false;
            }
        }
    }
}
