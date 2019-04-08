package com.java8;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * Created by blockWilling on 2019/1/23.
 */
@Service("interImplOnly")
@Scope(value = SCOPE_PROTOTYPE)
public  class interImpl implements inter,inter2 {
    public interImpl() {
        System.out.println();
    }

    /**
     * 实现两个接口，存在重名default方法，那么这时候子类就必须实现这个default方法
     */
    @Override
    public void see() {
//        System.out.println("see");
    inter2.super.see();
    }

    @Override
    public void abMEthod() {
System.out.println("com.java8.interImpl.abMEthod");
    }

    public static void main(String[] args) {
        inter inter=new interImpl();
        inter.see();
        inter.abMEthod();
        com.java8.inter.say();
    }
}
