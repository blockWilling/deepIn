package com.spring5.service;

import org.springframework.stereotype.Service;

/**
 * Created by 康金 on 2019/1/24.
 */
//@Service
public class SubSimpleService extends simpleService{
    @Override
    public void say1() {
        System.out.println("say2");
    }
}
