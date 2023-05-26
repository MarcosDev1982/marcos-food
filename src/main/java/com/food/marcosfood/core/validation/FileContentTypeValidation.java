package com.food.marcosfood.core.validation;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class FileContentTypeValidation implements ConstraintValidator<FileContentType, MultipartFile> {

    private List<String> allowedContentType;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowedContentType= Arrays.asList(constraintAnnotation.allowed());

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {

        return multipartFile == null || this.allowedContentType.contains(multipartFile.getContentType());
    }

}
