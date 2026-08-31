package com.example.demo.thread;

public class SimpleThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" is running");
            try {
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName()+" Finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" is running");
            try {
                Thread.sleep(20000);
                System.out.println(Thread.currentThread().getName()+" Finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        //t2.run();
        t1.join();
        System.out.println("main thread Terminated");

    }
}
