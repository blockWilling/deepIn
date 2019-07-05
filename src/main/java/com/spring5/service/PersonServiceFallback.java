package com.spring5.service;


import java.util.Collection;
import java.util.Collections;

/**
 * {@link PersonService} Fallback 实现
 *
 * @author 小马哥 QQ 1191971402
 * @copyright 咕泡学院出品
 * @since 2017/11/5
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
