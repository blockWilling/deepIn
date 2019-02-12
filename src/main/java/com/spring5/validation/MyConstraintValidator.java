package com.spring5.validation;

import com.spring5.entity.Person;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * Created by 康金 on 2019/2/12.
 */
public class MyConstraintValidator implements ConstraintValidator {
    @Override
    public void initialize(Annotation constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        return true;
    }
}
