package com.spring5.aop;

import com.spring5.service.StrongService;
import com.spring5.service.SubSimpleService;
import com.spring5.service.simpleService;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * 通过代码的方式生成aop代理
 */
public class ProgrammaticAspectJ {
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
}
