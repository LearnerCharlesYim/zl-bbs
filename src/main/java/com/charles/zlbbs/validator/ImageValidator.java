package com.charles.zlbbs.validator;

import com.charles.zlbbs.domain.anno.ImageRequired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageValidator implements ConstraintValidator<ImageRequired, MultipartFile> {

    private boolean required = false;

    @Override
    public void initialize(ImageRequired constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            if (file.isEmpty() || !file.getContentType().toLowerCase().startsWith("image")) {
                return false;
            }
            return file.getSize() <= 2 * 1024 * 1024;
        } else {
            if (!file.isEmpty()) {
                if (!file.getContentType().toLowerCase().startsWith("image")) {
                    return false;
                }
                return file.getSize() <= 2 * 1024 * 1024;
            } else {
                return true;
            }
        }
    }
}
