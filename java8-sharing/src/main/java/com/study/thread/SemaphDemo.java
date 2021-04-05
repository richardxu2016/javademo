package com.study.thread;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.Semaphore;

/**
 * 第12行声明了5个许可的信号量，意味着可以同时有5个线程访问17-19行，申请信号量是acquire();
 * 释放是release()
 */
public class SemaphDemo implements Runnable {

    Semaphore semaphore = new Semaphore(5) ;

    @Override
    public void run(){
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+" done!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
    public static void main(String[] args) {
//        ExecutorService executors = Executors.newFixedThreadPool(20) ;
//
//        SemaphDemo semaphDemo = new SemaphDemo();
//        for (int i = 0 ;i< 20;i++){
//            executors.submit(semaphDemo) ;
//        }
        T o = new T() ;

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}

class T {
    int m = 8 ;
}
