package com.spring5.service;


import java.util.Collection;
import java.util.Collections;

/**
 * {@link PersonService} Fallback 实现
 *
 */
public class PersonServiceFallback implements PersonService {

    @Override
    public String save() {
        return "fail method";
    }

    @Override
    public String saveUser(String msg) {
        return "";
    }

}
