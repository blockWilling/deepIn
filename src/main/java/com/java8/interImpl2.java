package com.java8;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * Created by blockWilling on 2019/1/23.
 */
@Service
public  class interImpl2<@anno("") t> implements inter,inter2 {
    public interImpl2() {
        System.out.println();
    }
    public interImpl2(interImpl inter) throws @anno("") Exception{
        System.out.println("com.java8.interImpl2.interImpl2(com.java8.interImpl)");
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
    public @anno("") void abMEthod() {
System.out.println("com.java8.interImpl.abMEthod");
    }

    public static void main(String[] args) {
        inter inter=new interImpl2();
        inter.see();
        inter.abMEthod();
        com.java8.inter.say();
    }
}
