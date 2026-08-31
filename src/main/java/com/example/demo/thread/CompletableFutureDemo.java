package com.example.demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    void main(){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
        
    }
}
