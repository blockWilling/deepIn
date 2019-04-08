package com.spring5.service.impl;

import com.spring5.service.StrongService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Created by blockWilling on 2019/2/13.
 */
@Service
public class StrongServiceImpl implements StrongService {
    @NonNull
    private String str=null;

    @Override
    public void strongSay1() {
        System.out.println("strongSay1");
    }

    @Override
    @NonNull
    public Object retNull() {

        return str;
    }
}
