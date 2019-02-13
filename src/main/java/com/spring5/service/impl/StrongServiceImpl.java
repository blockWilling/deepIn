package com.spring5.service.impl;

import com.spring5.service.StrongService;
import org.springframework.stereotype.Service;

/**
 * Created by 康金 on 2019/2/13.
 */
@Service
public class StrongServiceImpl implements StrongService{
    @Override
    public void strongSay1(){
        System.out.println("strongSay1");
    }
}
