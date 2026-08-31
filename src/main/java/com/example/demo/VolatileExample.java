package com.example.demo;

class SharedObj{
    private volatile boolean flag=false;

    public void readFlag(){
        while(!flag){

        }
        System.out.println("Flag set to true");
    }
    public void writeFlag(){
        flag = true;
    }
}
public class VolatileExample {
    public static void main(String[] args) {
        SharedObj sharedObj = new SharedObj();
        Thread writerThread = new Thread(()->{
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            sharedObj.writeFlag();
        });
        Thread readerThread = new Thread(sharedObj::readFlag);
        writerThread.start();
        readerThread.start();
    }
}
