package com.spring5.deepIn;

import com.spring5.anno.ExcludeFromComponentScan;
import com.spring5.beanNameGenerators.MyBeanNameGenerator;
import com.spring5.conf.CustomRibbonClientConf;
import com.spring5.event.myEvent;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.*;
import org.springframework.context.event.EventListener;

/**
 * {@link EnableAspectJAutoProxy}实际{@link Import}了一个{@link AspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar}
 * 去注册了 {@link AnnotationAwareAspectJAutoProxyCreator}
 * {@link BeanNameAutoProxyCreator}与{@link AnnotationAwareAspectJAutoProxyCreator}类似，不过使用自己的neanName匹配原则,
 * 实现的原理就是通过重写 {@link org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#getAdvicesAndAdvisorsForBean}
 * 方法实现自己的匹配advisor原则
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.spring5", "com.java8"}, nameGenerator = MyBeanNameGenerator.class,excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class ))
@PropertySource("classpath:properties/myPro.sql")
//@PropertySource("classpath*:com/**/simpleService.java")
//@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableCircuitBreaker
@RibbonClient(name="myRibbonConf",configuration = CustomRibbonClientConf.class)
public class DeepInApplication {

    //	private DeepInApplication(){}
    @Bean
    public static String out() {
        return "11";
    }

    public static void main(String[] args) {
        SpringApplication.run(DeepInApplication.class, args);
//		new SpringApplicationBuilder()
//				.sources(DeepInApplication.class,
//						normalBeanConf.class)
//				.run(args);
//System.out.print("22");
    }

    @EventListener(myEvent.class)
    public void receieveMyEvent(myEvent event) {
        System.out.println(event.getSource().toString());
    }


}

