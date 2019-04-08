package com.spring5.aop;

import com.spring5.service.StrongService;
import com.spring5.service.impl.StrongServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by blockWilling on 2019/2/13.
 */
@Component
@Aspect
public class AopConfig {
    /**
     * 通过 {@link DeclareParents},为 {@link DeclareParents#value()}添加类扩展
     */
    // “...aop.Person”后面的 “+” 号，表示只要是Person及其子类都可以添加新的方法
   @DeclareParents(value = "com.spring5.service.SimpleService", defaultImpl = StrongServiceImpl.class)
    public StrongService skill;


    @Around("methodsToBeProfiled() || methodsToBeProfiled2()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("@Around-before");
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
            System.out.println("@Around-after");
        }
    }
    @Before("methodsToBeProfiled()|| methodsToBeProfiled2()")
    public void doAccessCheck() {
        System.out.println("@Before");
    }
    @AfterReturning("methodsToBeProfiled()|| methodsToBeProfiled2()")
    public void doAfterRet() {
        System.out.println("@AfterReturning");
    }
    @AfterThrowing("methodsToBeProfiled()|| methodsToBeProfiled2()")
    public void doAfterThrow() {
        System.out.println("@AfterThrowing");
    }
    @After("methodsToBeProfiled()|| methodsToBeProfiled2()")
    public void doAfter() {
        System.out.println("@After");
    }
    @Pointcut("execution(public * com.spring5.service.StrongService.strongSay1(..))")
    public void methodsToBeProfiled(){}
    @Pointcut("execution(public * com.spring5.service.SimpleService.*(..))")
    public void methodsToBeProfiled2(){}
}
