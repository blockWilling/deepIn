package com;

import javax.security.sasl.SaslException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author kangjin
 * @since 2020/10/9 14:07
 */
public class vvv {
    static int a=2;
    public static void main(String[] args) {

        CopyOnWriteArrayList a=new CopyOnWriteArrayList();
        a.iterator();
        ArrayList aa=new ArrayList();
        aa.iterator();
//        change(a);
        ie ie = new ie();
        changeIE(ie);
        System.out.println(a);
        System.out.println(ie.a);
    }
    static void change(int a){
        a+=33;
    }

    static void changeIE(ie a){
       a.a+="fff";
    }
    static class ie{
        String a="www";
    }
}

