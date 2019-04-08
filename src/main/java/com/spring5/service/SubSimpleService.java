package com.spring5.service;

import com.spring5.servlet.filter.simpleFilter;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by blockWilling on 2019/1/24.
 */
//@Service
@Named
public class SubSimpleService extends SimpleService {
//    @Inject
//    simpleFilter simplefilter;

    @Override
    public void say1() {
        System.out.println("say2");
    }
}
