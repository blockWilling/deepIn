package com.spring5.service;

import feign.Contract;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * {@link FeignClient}还可以配置configuration属性，来代替{@link Contract},{@link RequestInterceptor}等默认配置
 */

@FeignClient(value = "user-service-provider",fallback = PersonServiceFallback.class) // 服务提供方应用的名称
public interface PersonService {

    @RequestMapping(value = "/feignSave")
    String save();

    @RequestMapping(value = "/user/save")
    String saveUser(String msg);
}
