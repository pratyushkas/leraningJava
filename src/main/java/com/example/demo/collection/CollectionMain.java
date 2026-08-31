package com.example.demo.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CollectionMain {
    public static void main(String[] args) {
        Person p1 = new Person("John", 1);
        Person p2 = new Person("Jane", 2);
        Person p3 = new Person("John", 1);

        Map<Person,Integer> map = new HashMap<>();
        map.put(p1, 100);
        map.put(p2, 200);
        map.put(p3, 300);
        System.out.println(map);
        System.out.println(map.size());
        ConcurrentMap<Person,Integer> concurrentHashMap = new ConcurrentHashMap<>();

    }
}
