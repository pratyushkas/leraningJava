package com.example.demo.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailsafeVsFaleFast {
    public static final int ITERATOR = 100000;

    public static void failFastDemo(){
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<=ITERATOR;i++){
            list.add(i);
        }
        long start = System.nanoTime();
        for(Integer i:list){
            Math.sqrt(i);
        }
        long end = System.nanoTime();
        System.out.println("Time taken by FailFast Iterator: " + (end-start)/1000000);
    }

    public static void failSafeDemo(){
        List<Integer> list = new CopyOnWriteArrayList<Integer>();
        for(int i=0;i<=ITERATOR;i++){
            list.add(i);
        }
        long start = System.nanoTime();
        for(Integer val:list){
            Math.sqrt(val);
        }
        long end = System.nanoTime();
        System.out.println("Time taken by FailSafe Iterator: " + (end-start)/1000000);
    }

    void main(){
        failFastDemo();
        failSafeDemo();
    }
}
