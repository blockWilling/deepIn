package com.spring5.validation;

import com.spring5.entity.Person;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by 康金 on 2019/2/12.
 */
@Component
public class PersonValidator implements Validator,ApplicationContextAware {
    private ApplicationContext applicationContext;
    /**
     * This Validator validates *only* Person instances
     */
    public boolean supports(Class clazz) {
        return Person.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
//        ValidationUtils.rejectIfEmpty(e, "name", applicationContext.getBean(ResourceBundleMessageSource.class).getMessage("argument.required", new Object[]{"userDao"}, "Required", null));
        ValidationUtils.rejectIfEmpty(e, "name","argument.required");
        Person p = (Person) obj;
        if (p.getAge() < 0) {
            e.rejectValue("age", "argument.required");
        } else if (p.getAge() > 110) {
            e.rejectValue("age", "too.darn.old");
        }
        System.out.println(e.getAllErrors());
//        ValidationUtils.invokeValidator(new PersonValidator(), obj,new BeanPropertyBindingResult(obj,"person"));

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}