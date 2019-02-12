package com.spring5.service;

import com.java8.NewDate;
import com.java8.interImpl;
import com.java8.interImpl2;
import com.spring5.anno.MyComponent;
import com.spring5.servlet.filter.simpleFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by 康金 on 2019/1/24.
 */
@Service
    @MyComponent("MyComponent-simpleService")
public class simpleService {
    @Autowired
    simpleFilter simplefilter;
    @Bean
    public static String liteBean(NewDate newDate){
        return "liteBean"+newDate;
    }

    public void say1(){
        System.out.println("say1");
    }
}
