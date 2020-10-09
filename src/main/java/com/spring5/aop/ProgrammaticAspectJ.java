package com.spring5.aop;

import com.spring5.service.StrongService;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ApplicationContext;

/**
 * 通过代码的方式生成aop代理,两者的实现原理其实是相同
 */
public class ProgrammaticAspectJ {
    /**
     * 通过 {@link AspectJProxyFactory}
     * @param service
     * @return
     */
    public static StrongService create(StrongService service){
        // create a factory that can generate a proxy for the given target object
        AspectJProxyFactory factory = new AspectJProxyFactory(service);

// add an aspect, the class must be an @AspectJ aspect
// you can call this as many times as you need with different aspects
        factory.addAspect(AopConfig.class);

// you can also add existing aspect instances, the type of the object supplied must be an @AspectJ aspect
//        factory.addAspect(usageTracker);

// now get the proxy object...
        StrongService proxy = factory.getProxy();
        return proxy;
    }

    /**
     * 通过{@link ProxyFactoryBean}
     * @param service
     * @param applicationContext
     * @return
     */
    public static StrongService createByProxyFactoryBean(StrongService service, ApplicationContext applicationContext){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(service);
        proxyFactoryBean.setBeanFactory(applicationContext);
        proxyFactoryBean.setSingleton(true);
        proxyFactoryBean.setTargetSource();
        /**
         * 这里不保存bean实例的原因是： 如果 {@link ProxyFactoryBean#singleton}为false，并且advisor是多例的，那么就无法使用
         */
        proxyFactoryBean.setInterceptorNames("transactionInterceptor");
        return (StrongService) proxyFactoryBean.getObject();
    }
}
