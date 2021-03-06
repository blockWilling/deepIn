package com.spring5.validation;

import com.spring5.anno.MyConstraint;
import com.spring5.entity.Person;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * 为字段定义的{@link ConstraintValidator}
 */
@Component
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,String> {

    @Override
    public void initialize(MyConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value!=null&&value.length()<4;
    }
}
