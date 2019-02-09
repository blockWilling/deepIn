package com.java8;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * Created by 康金 on 2019/1/30.
 */
@Component
public class lookupImpl {
    public lookupImpl() {
        System.out.println();
    }

    /**
     * 一般在需要注入的bean不是单例的情况下使用
     * @return
     */
    @Lookup("interImplOnly")
    public interImpl getInterImpl(){return new interImpl() ;}
}
