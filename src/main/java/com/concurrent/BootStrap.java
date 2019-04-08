package com.concurrent;

/**
 * Created by blockWilling on 2019/3/25.
 */
public  class BootStrap {
    static SyncDemo syncDemo=new SyncDemo();

    public static void main(String[] args) throws InterruptedException {
        new  Thread(()->{
            try {
                synchronized (syncDemo.a){
                    Thread.sleep(3000);
                }
//                SyncDemo.see();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(100);
        System.out.println( syncDemo.a.toString());
        SyncDemo.see2();

    }
    void m(){
    }
}
