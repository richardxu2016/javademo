package com.study.thread;

import java.util.concurrent.locks.ReentrantLock;

public class TryLock implements Runnable {

    public static ReentrantLock l1 = new ReentrantLock() ;
    public static ReentrantLock l2 = new ReentrantLock() ;

    int lock ;

    public TryLock(int lock){
        this.lock = lock ;
    }

    @Override
    public void run() {

        // 如果是1 先申请lock1 再申请l2； 反之，则相反
       if (lock == 1){
           while (true){
               if (l1.tryLock()){
                   try {
                       try {

                           Thread.sleep(50);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }

                       if (l2.tryLock()){
                           try {
                               System.out.println(Thread.currentThread().getName()+" my job done!");
                               return;
                           }finally {
                               l2.unlock();
                           }
                       }
                   }finally {
                       l1.unlock();
                   }
               }
           }
       }else {
           while (true){
               if (l2.tryLock()){
                   try{
                       try {
                           Thread.sleep(50);
                       }catch (InterruptedException e){
                            e.printStackTrace();
                       }
                       if (l1.tryLock()){
                           try {
                               System.out.println(Thread.currentThread().getName()+" my job done !");
                               return;
                           }finally {
                               l1.unlock();
                           }
                       }
                   }finally {
                       l2.unlock();
                   }
               }
           }
       }
    }

    public static void main(String[] args) {
        TryLock lock1 = new TryLock(1) ;
        TryLock lock2 = new TryLock(2) ;
        Thread t1 = new Thread(lock1) ;
        Thread t2 = new Thread(lock2) ;
        t1.start();
        t2.start();
    }
}
