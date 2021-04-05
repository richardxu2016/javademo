package com.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock() ;
    @Override
    public void run() {

        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)){
                System.out.println("thread:"+Thread.currentThread().getName()+" success");
                Thread.sleep(6000);
            }else {
                System.out.println("thread:"+Thread.currentThread().getName()+" get lock failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock l1 = new TimeLock();
        TimeLock l2 = new TimeLock() ;
        Thread t1 = new Thread(l1) ;
        Thread t2 = new Thread(l2) ;
        t1.start();
        t2.start();
    }
}
