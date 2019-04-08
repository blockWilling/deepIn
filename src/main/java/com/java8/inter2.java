package com.java8;

/**
 * Created by blockWilling on 2019/1/22.
 */
public interface inter2 {
    //default方法
    default void see(){
System.out.println("com.java8.inter222.see");
    }
    //公共静态方法
     static void say(){System.out.println("com.java8.inter222.say");}
     void abMEthod();
}
