package com.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import java.util.stream.TerminalOp;

/**
 * ①Stream 自己不会存储元素。
 * ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 */
public  class stream {
    public  void sw1(){
        System.out.println("origin");
    }
public lamda look(){
        return null;
}
    public static void main(String[] args) {
        new ArrayList<>().stream();
        Arrays.stream(new String[3]);
        Stream.of(new String[3]);
        Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13)
                //===============中间操作开始，如果只有中间操作，不会执行中间操作的逻辑
                .limit(10)
                .skip(1)
                .filter((a)->{return a<13;})
                .distinct() //根据equals方法判断去重
                //===============中间操作结束
     //           .forEach(System.out::println)
        ;
        Stream.generate(new Object()::hashCode);
//对每个元素进行map的函数操作，返回泛型对象
       Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).map((a)->a+1).forEach(System.out::println);
  //对每个元素进行flatmap的函数操作返回新的stream，并最终合并为一个流
     //   Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).flatMap((a)->Stream.of(10,11,12,13)).forEach(System.out::println);
      //sort传入comparator进行排序，或者不传进行自然排序(没有实现Comparable抛出异常)
      //  Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).sorted((a,b)->b-a).forEach(System.out::println);
/*/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
        /*System.out.println( Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).allMatch((a)->a>10));
        System.out.println( Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).anyMatch((a)->a>10));
        System.out.println( Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).noneMatch((a)->a>19));
        System.out.println( Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).findFirst().get());
        System.out.println( Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).findAny().get());*/
        /**
         * 并行流的操作返回的是一个 task{@link TerminalOp#evaluateParallel(java.util.stream.PipelineHelper, java.util.Spliterator)}
         * 每次parallelStream并行流中间操作的evaluate会返回一个task，即一种fork/join的模式，如果一个task没有任务了，会从其他task上偷取一个任务来执行，已达到充分利用cpu多核的特性
         */
        /*System.out.println(Arrays.asList(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).parallelStream().findAny().get());
        System.out.println( Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).count());
        System.out.println( Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).max((a,b)->b-a).get());
        System.out.println( Stream.of(0,1,2,3,4,5,5,6,7,8,9,10,11,12,13).min((a,b)->b-a).get());
       //求和
        System.out.println( Stream.of(0,1,2,3,4,5,6,7,8,9,10,11,12,13).reduce((a,b)->a+b).get());
        //手机stream中的数据
        List<Integer> collect = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13).filter((a) -> a > 9).collect(Collectors.toList());
        collect.forEach(System.out::println);
        //制定具体返回的collection实现类类型
        List<Integer> collect2 = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13).filter((a) -> a > 9).collect(Collectors.toCollection(LinkedList::new));
        collect2.forEach(System.out::println);
        //collector还支持：总和，最大值，平均值,分组等
        Map<Boolean, List<Integer>> collect1 = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13).collect(Collectors.groupingBy((a) -> a.equals(2)));
        System.out.println(collect1);*/
        //关于optional的设计，即有可能为null的对象就封装到optional中
        Optional<Integer> integer = Optional.of(12);
        System.out.println(integer.get());
//        Optional.empty().get();//NoSuchElementException，optional没有对应的封装元素
        System.out.println(Optional.empty().isPresent());
        System.out.println(Optional.empty().orElse(122));
        System.out.println(Optional.empty().orElseGet(()->2));
    }
}
