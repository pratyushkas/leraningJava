package com.example.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWrite {
    private int count = 0;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public void readCount(){
        try{
            readLock.lock();
            System.out.println("read count: " + count);
        }finally {
            readLock.unlock();
        }
    }

    public void writeCount(){
        try{
            writeLock.lock();

            count++;
            System.out.println("Counter Updated to: " + count);
        }finally {
            writeLock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWrite readWrite = new ReentrantReadWrite();
        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                readWrite.readCount();
            }
        };
        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                readWrite.writeCount();
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<?>> futures = new ArrayList<>();

        futures.add(executor.submit(writeTask));
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        futures.add(executor.submit(readTask));
        futures.add(executor.submit(writeTask));
        futures.add(executor.submit(readTask));
        futures.add(executor.submit(readTask));
        for(Future future:futures){
            try {
                future.get();
            }catch (Exception e){}
        }

        executor.shutdown();

    }
}
