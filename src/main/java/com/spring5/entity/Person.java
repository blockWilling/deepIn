package com.spring5.entity;

import com.spring5.anno.MyConstraint;
import com.spring5.service.StrongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@MyConstraint(message = "用户名不是root，验证错误")
@Configurable
public class Person {
    @Autowired
    StrongService strongService;
@NotNull
//@MyConstraint
    private String name;
@Min(18)
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public StrongService getStrongService() {
        return strongService;
    }

    public void setStrongService(StrongService strongService) {
        this.strongService = strongService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
// the usual getters and setters...
}
