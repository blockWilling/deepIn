package com;

import com.spring5.entity.Inventor;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by blockWilling on 2019/5/27.
 */
public class JvmDemo extends Inventor {
        private int value = 1;
        public String s = "abc";
        public final  int f = 12;
//         static {
//
//                i=0;
////                System.out.println(i);
//
//        }

        public static void main(String[] args) throws SocketException {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            String s = new String("1");
            s.intern();
            String s2 = "1";
            System.out.println(s == s2);

            String s3 = new String("1") + new String("1");
            s3.intern();
            String s4 = "11";
            System.out.println(s3 == s4);
                System.out.println(i);
//                Consumer s=(a)->{System.out.println(a);};
//                s.accept(1);
//                Supplier u=()->{return s;};
//                Predicate p=(c)->{return false;};
//                Function f=(a)->{return "";};
        }
    final static int i=1;
    final static int j=1;
//        JvmDemo jvmDemo = new JvmDemo();
//        Object o=new Object();

}
