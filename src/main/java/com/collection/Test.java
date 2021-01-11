package com.collection;

/**
 * @author kangjin
 * @since 2020/12/1 10:02
 */
public class Test {

    public  void myMethod(String str) {

        System.err.println("string");

    }

    public  void myMethod(Object obj) {

        System.err.println("object");

    }

    public  void myMethod(Integer obj) {

        System.err.println("integer");

    }

    public static void main(String[] args) {

        Test t = new Test();

        t.myMethod((String)null);

        String str1 = "a";
        String str2 = "a";
        String str3 = new String("a");
        System.err.println(str1 == str2);
        System.err.println(str1 == str3);
        str3 = str3.intern();
        System.err.println(str1 == str3);
        Test innerDemo01=new Test();
        Bar bar=innerDemo01.method();
        bar.show();//你觉得num会输出吗？？？？
    }

    class Bar{
        public void show(){
            //do ..
        }
    }
    public Bar  method(){
        //String str="wuranghao";
        int num=30;
        //局部内部类将输出这个局部变量
        class innerClass extends Bar{

            public void show(){
                System.out.println(num);
            }
        }
        return new innerClass();
    }

}
