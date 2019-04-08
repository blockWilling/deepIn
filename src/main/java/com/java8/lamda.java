package com.java8;

import com.spring5.deepIn.DeepInApplication;
import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.function.*;

/**
 * Created by blockWilling on 2019/1/22.
 */
public class lamda implements MethodReplacer{
    public void sw1(){
        System.out.println("sw1");
    }
    public static void main(String[] args) {
        new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        } ;
        Comparator<String> stringComparator = (b,c) -> {
            return 1;
        };
        Consumer s=(a)->{System.out.println(a);};
        Supplier u=()->{return s;};
        Predicate p=(c)->{return false;};
        Function f=(a)->{return "";};
        //条件是抽象方法的参数类型与返回值类型与引用方法(静态 or 实例)一致
        //对象实例引用 and 静态引用：
        Consumer s2=System.out::println;
        s2.accept("vvv");
        Object o = new Object();
        Supplier supplier=o::hashCode;
        System.out.print(supplier.get());
        Predicate<Integer> predicate=o::equals;
        s2.accept(predicate.test(1));
        Function<Integer,Boolean> function=o::equals;
        s2.accept(function.apply(1));

        Comparator<Integer> comparator=Integer::compare;
        s2.accept(comparator.compare(1,2));
        //类名引用：当抽象方法两个入参，一个是方法的调用者，一个是方法的入参，可以使用类名::方法名
        BiPredicate<String,String> comparator0=(a,b)->a.equals(b);
        BiPredicate<String,String> comparator1=String::matches;
        s2.accept(comparator1.test("1","\\D"));
        //构造器引用：
        Supplier consumer=DeepInApplication::new;
        s2.accept(((DeepInApplication)consumer.get()).out());
    }

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("replaced");
        return null;
    }
}
