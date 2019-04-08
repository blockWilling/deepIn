package com.concurrent;

/**
 * Created by blockWilling on 2019/3/25.
 */
public class SyncDemo {
    volatile String[] strings;
    public  String a="000";
    static Object c=new Object();
    String b="001";
   public synchronized static void see() throws InterruptedException {
       Thread.sleep(3000);
           System.out.println(1);
    }
    public synchronized static void see2() throws InterruptedException {
        System.out.println(2);
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    class inner{
        void see(){
            System.out.println(11);
        }
    }
}
