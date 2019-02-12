package com.spring5.controller;

import com.java8.NewDate;
import com.java8.*;
import com.others.importConf;
import com.spring5.entity.Person;
import com.spring5.event.myEvent;
import com.spring5.service.simpleService;
import com.spring5.servlet.filter.simpleFilter;
import com.spring5.validation.PersonValidator;
import org.apache.catalina.session.StandardSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.*;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

/**
 * Created by 康金 on 2019/1/24.
 */
@RestController

@Import({importConf.class})
@ImportResource({"classpath:**/importSourceFile.xml"})
@RequestMapping("/")
@RequestScope
//@SessionScope
//@ApplicationScope
public class simpleTest implements ApplicationContextAware,ApplicationEventPublisherAware,ApplicationListener<myEvent> {
    private ApplicationContext applicationContext;
    @Autowired
    private Validator validator;
    @Autowired
    Environment env;
    @Autowired
    NewDate newDate;
    @Autowired
    simpleService simpleService;
    @Autowired
    @Qualifier("lamda")
    lamda lamda;

    @Autowired
    simpleFilter simplefilter;
    /**
     * 在importSourceFile.xml中为其中设置的stream的bean设置了primary=true，所以不会报错,
     *      可以使用{@link org.springframework.context.annotation.Primary}与{@link org.springframework.context.annotation.Bean}一起使用去代替
     * 如果xml中设置了autowire-candidate=false，那么@Autowired失效，容器启动报错
     */
    @Autowired
            @Resource
//    @Qualifier("xmlBean")
    stream stream;
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
            private PersonValidator personValidator;
    /**
     * 如果加上了{@link RequestScope},那么成员变量只在一个请求内保留
     * 如果加上了{@link SessionScope},那么交给session的{@link StandardSession#attributes}管理
     * 如果加上了{@link ApplicationScope},就交给ApplicationContext#attributes去处理
     */
    int i=0;
    @GetMapping("hello")
    public String see(HttpServletRequest a,Person p, String in, Session session){
//        applicationContext.getBean("newDate1");
//        applicationContext.getBean("simpleFilter");
        Person person = new Person();
        person.setAge(1);
        //通过@Autowired注入的validator，去手动 检验对象
        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        System.out.println(validate);
        personValidator.validate( person,new BeanPropertyBindingResult(person,"person"));

        new NewDate();
        i++;
        newDate.say();
        String required = applicationContext.getBean(ResourceBundleMessageSource.class).getMessage("argument.required", new Object[]{"userDao"}, "Required", null);
        System.out.println(required);
        System.out.println(env.getProperty("realUser.name"));
      System.out.println(a.getServletContext().getInitParameter("param1"));
        return "hello world!";
    }

    /**
     * {@link Valid}验证请求传入的对象
     * @param person
     * @return
     */
    @GetMapping("getPerson")
    public String getPerson(@Valid Person person){
        return "getPerson";
    }
    @GetMapping("publishEvent")
    public String publishEvent(){
        String  source="publishEvent";
        applicationEventPublisher.publishEvent(new myEvent(source) {});
        return "publishEvent";
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher=applicationEventPublisher;
    }

    @Override
    public void onApplicationEvent(myEvent event) {

        if(event instanceof myEvent)
        System.out.println(event.getSource().toString());
    }

}
