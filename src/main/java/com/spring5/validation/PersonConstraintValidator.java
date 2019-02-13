package com.spring5.validation;

import com.spring5.anno.MyConstraint;
import com.spring5.entity.Person;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 为字段定义的{@link ConstraintValidator}
 */
@Component
public class PersonConstraintValidator implements ConstraintValidator<MyConstraint,Person> {

    @Override
    public void initialize(MyConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Person value, ConstraintValidatorContext context) {
        return value.getName()!=null&&value.getName().equals("root");
    }
}
