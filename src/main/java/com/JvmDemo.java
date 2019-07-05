package com;

import com.spring5.deepIn.DeepInApplication;
import com.spring5.entity.Inventor;

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
         static {

                i=0;
//                System.out.println(i);

        }

        public static void main(String[] args) {
                System.out.println(i);
//                Consumer s=(a)->{System.out.println(a);};
//                s.accept(1);
//                Supplier u=()->{return s;};
//                Predicate p=(c)->{return false;};
//                Function f=(a)->{return "";};
        }
       static int i=1;
       static int j=1;
//        JvmDemo jvmDemo = new JvmDemo();
//        Object o=new Object();

}
