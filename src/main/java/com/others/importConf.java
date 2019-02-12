package com.others;

import com.java8.NewDate;
import com.java8.interImpl;
import com.java8.lamda;
import com.spring5.beanFactoryPostProcessor.MybeanFactoryPostProcessor;
import com.spring5.beanPostProcessor.MyBeanPostProcessor;
import com.spring5.entity.Person;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * {@link Profile}value与当前激活的profile不一致，则为无效配置类
 */
@Configuration
@Profile("test")
public class importConf implements InitializingBean {
    public importConf() {
        System.out.println("com.others.importConf");
    }

    private interImpl inter = new interImpl();

    @Bean
    public lamda newLamda() {
        return new lamda();
    }

    @Bean
    public static MybeanFactoryPostProcessor mybeanFactoryPostProcessor() {
        return new MybeanFactoryPostProcessor();
    }

    @Bean
    public static MyBeanPostProcessor mybeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
@Bean
public Person person(){
        return new Person();
}
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是包外的配置类");
    }
}
