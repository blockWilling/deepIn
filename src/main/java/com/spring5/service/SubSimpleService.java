package com.spring5.service;

import com.spring5.servlet.filter.simpleFilter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by 康金 on 2019/1/24.
 */
//@Service
@Named
public class SubSimpleService extends simpleService {
    @Inject
    simpleFilter simplefilter;

    @Override
    public void say1() {
        System.out.println("say2");
    }
}
