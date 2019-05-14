package com.spring5.controller;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpoint;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 暂不知道如何使用
 * http://localhost:9090/actuator/httptrace通过一个{@link HttpTraceFilter}去记录trace，可以自定义{@link HttpTraceRepository}
 * 去实现自己的记录方式，默认注入的{@link InMemoryHttpTraceRepository}有注入条件{@link ConditionalOnMissingBean}
 * Created by blockWilling on 2019/5/10.
 */
//@ControllerEndpoint(id = "MyActuatorEndpoint")
//@RestController
public class MyActuatorEndpoint {

    public String listNames() {
        return "MyActuatorEndpoint";
    }
}
