package com.example.demo.thread;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.Getter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    @Getter
    private int balance;
    public BankAccount(int balance) {
        this.balance = balance;
    }

    private final Lock lock = new ReentrantLock();
    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName() + " is trying to acquire lock");
        try {
            if(lock.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " acquired the lock");
                if (amount <= balance) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " Initating withdrawal process");
                        Thread.sleep(1000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " Done with withdraw process. Current Balance is: "+getBalance());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName() + " released the lock");
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " Not enough balance");
                }
            }else {
                System.out.println(Thread.currentThread().getName() + " Could not acquire lock");
            }
        }catch (Exception e){
            System.out.println(Thread.currentThread().getName() + " Exception occurred: " + e.getMessage());
        }
    }
}
