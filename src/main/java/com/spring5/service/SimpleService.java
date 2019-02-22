package com.spring5.service;

import com.java8.NewDate;
import com.java8.interImpl;
import com.java8.interImpl2;
import com.spring5.anno.MyComponent;
import com.spring5.entity.Person;
import com.spring5.servlet.filter.simpleFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
//import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * {@link Validated}+{@link MethodValidationPostProcessor}去给所有方法加上入参bean校验
 */
@Validated
@Service()
    @MyComponent("MyComponent-simpleService")
@Transactional(propagation = Propagation.NESTED,rollbackFor = {ClassCastException.class})
public class SimpleService {
    private String sysUserName;
    @Bean
    public static String liteBean(NewDate newDate){
        return "liteBean"+newDate;
    }

    public void say1(){

        System.out.println("say1");
        StrongService integer = (StrongService) this;
    }

    public void  validatePerson(Person person,  String nb){

    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

}
