package com.spring5.anno;

import com.spring5.validation.MyConstraintValidator;
import com.spring5.validation.PersonConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 可以定义多个{@link Constraint#validatedBy()},比如这里的验证string和Person
 */
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={MyConstraintValidator.class, PersonConstraintValidator.class})
public @interface MyConstraint {
    String message() default "{length of name can not over 4}";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}