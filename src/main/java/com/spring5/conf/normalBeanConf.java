package com.spring5.conf;

import com.java8.NewDate;
import com.java8.interImpl;
import com.java8.interImpl2;
import com.spring5.beanDefinition.testFilterRegistrationBean;
import com.spring5.beanDefinition.testFrameworkServletRegistrationBean;
import com.spring5.controller.simpleTest;
import com.spring5.entity.Person;
import com.spring5.servlet.SimpleServlet;
import com.spring5.servlet.filter.simpleFilter;
import com.spring5.servlet.testFrameworkServlet;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.DispatcherServlet;
import sun.nio.cs.StreamDecoder;

import java.util.Date;
import java.util.HashSet;

/**
 * Created by blockWilling on 2019/1/24.
 */
@Configuration

public class normalBeanConf  {

    public normalBeanConf() {

        System.out.println("com.spring5.conf.normalBeanConf");
    }

    private interImpl inter = new interImpl();

    /**
     * org.springframework.context.annotation.Bean#value()设置多个相当于xml中配置
     *       <alias name="myApp-dataSource" alias="subsystemA-dataSource"/>
            <alias name="myApp-dataSource" alias="subsystemB-dataSource"/>
     * 实际上@Bean的方式注入bean就是把需要注入的beanDefinition设置faactoryMethodName和factoryBeanName，类似如下XML方式注入
     *      <bean id="clientService"
                 factory-bean="serviceLocator"
                 factory-method="createClientServiceInstance"/>
     * @return
     */
    @Bean(value = {"newDate1","newDate2"},autowire = Autowire.BY_TYPE,initMethod = "initNewDate",destroyMethod ="destroyNewDate" )
    public  NewDate newDate() {
        return new NewDate();
    }

/*    @Bean
    public testFrameworkServletRegistrationBean testFrameworkServletRegistrationBean() {
//        Tomcat.addServlet("simpleServlet","com.spring5.servlet.SimpleServlet");
        return new testFrameworkServletRegistrationBean(new SimpleServlet(),2);
    }

        @Bean
    public testFilterRegistrationBean testFilterRegistrationBean() {
//        Tomcat.addServlet("simpleServlet","com.spring5.servlet.SimpleServlet");
        return new testFilterRegistrationBean(new simpleFilter(),new ServletRegistrationBean<?>[]{});
    }*/
    @Component
    @DependsOn("simpleTest")
   class innerConf{
       public innerConf() {
           System.out.println();
       }
   }

/*        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public  simpleFilter simpleFilter(){
            return  new simpleFilter();
        }*/

//        @Bean
//        @Qualifier()
//    public simpleTest simpleTest11(){
//        return  new simpleTest();
//        }
@Bean("bbbbean1")
public interImpl bean1(){
    return new interImpl();
}

    /**
     * 在{@link ConfigurationClassEnhancer.BeanMethodInterceptor#intercept(java.lang.Object, java.lang.reflect.Method, java.lang.Object[], org.springframework.cglib.proxy.MethodProxy)}
     * 中拦截，根据@Bean method的@Bean Name从beanFactory中去getBean
     * @return
     */
    @Bean
    public interImpl2 bean2(){
        interImpl inter = bean1();
        return new interImpl2(inter);
    }

    /**
     * messageSource，去classpath的root路径下查找baseName.properties文件
     * @return
     */
    @Bean
    public ResourceBundleMessageSource ResourceBundleMessageSource(){
    ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
    resourceBundleMessageSource.setBasename("exception");
    return  resourceBundleMessageSource;
}
@Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
}


}
