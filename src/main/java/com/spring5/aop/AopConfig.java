package com.spring5.aop;

import com.spring5.service.StrongService;
import com.spring5.service.impl.StrongServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by 康金 on 2019/2/13.
 */
@Component
@Aspect
public class AopConfig {
    /**
     * 通过 {@link DeclareParents},为 {@link DeclareParents#value()}添加方法
     */
    // “...aop.Person”后面的 “+” 号，表示只要是Person及其子类都可以添加新的方法
    @DeclareParents(value = "com.spring5.service.simpleService", defaultImpl = StrongServiceImpl.class)
    public StrongService skill;


    @Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }
    }

    @Pointcut("execution(public * com.spring5.service.StrongService.strongSay1(..))")
    public void methodsToBeProfiled(){}
}
