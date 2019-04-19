package com.concurrent;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by blockWilling on 2019/4/16.
 */
public class LockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock reentrantLock=new ReentrantReadWriteLock();

/*        new Thread(()->{
            reentrantLock.readLock().lock();
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            reentrantLock.readLock().unlock();
        }).start();*/
//        Thread.sleep(5000);
        reentrantLock.readLock().lock();
        reentrantLock.writeLock().lock();
        reentrantLock.writeLock().unlock();
        reentrantLock.readLock().unlock();
    }
}
