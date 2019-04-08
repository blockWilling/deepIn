package com.java8;

/**
 * Created by blockWilling on 2019/1/22.
 */

public interface inter {
    //default方法
    default void see(){
System.out.println("com.java8.inter.see");
    }
    //公共静态方法
     static void say(){System.out.println("com.java8.inter.say");}
     void abMEthod();
}
