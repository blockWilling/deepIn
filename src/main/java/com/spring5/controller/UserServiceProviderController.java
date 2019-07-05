package com.spring5.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring5.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Random;

/**
 * 实现了feign的接口，就会把{@link RequestMapping}的方法注解信息带过来
 */
@RestController
public class UserServiceProviderController implements PersonService {
    @Autowired
    PersonService personService;
    @Autowired
    private RestTemplate restTemplate;
    private final static Random random = new Random();

    @Override
    @ResponseBody
    public String saveUser(String msg) {
        return msg;
    }

    /**
     * 当{@link #helloWorld} 方法调用超时或失败时，
     * fallback 方法{@link #errorContent()}作为替代返回
     *
     * @return
     */
    @GetMapping("hello-world")
    @HystrixCommand(
            fallbackMethod = "errorContent",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "10")
            }

    )
    public String helloWorld() throws Exception {

        // 如果随机时间 大于 100 ，那么触发容错
        int value = random.nextInt(20);

        System.out.println("helloWorld() costs " + value + " ms.");

        Thread.sleep(value);

        return "Hello,World";
    }

    public String errorContent() {
        return "Fault";
    }

    @Override
    public String save() {
        String save = personService.saveUser("feigntest");
        return save;
    }
}
