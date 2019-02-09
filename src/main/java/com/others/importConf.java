package com.others;

import com.java8.NewDate;
import com.java8.interImpl;
import com.java8.lamda;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 康金 on 2019/1/24.
 */
@Configuration
public class importConf implements InitializingBean{
    public importConf() {
        System.out.println("com.others.importConf");
    }
private interImpl inter=new interImpl();
     @Bean
    public lamda newLamda(){
        return new lamda();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是包外的配置类");
    }
}
