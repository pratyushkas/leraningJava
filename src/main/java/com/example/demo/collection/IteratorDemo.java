package com.example.demo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class IteratorDemo {
    void main() throws InterruptedException {
        // FailFast: ArrayList, HashMap, HashSet
        // FailSafe: CopyOnWriteArrayList, ConcurrentHashMap
        List<String> list = new ArrayList<>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
           // System.out.println(item);
            if (item.equals("Item 3")) {
                iterator.remove();
            }
        }
        //System.out.println(list);

        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("Item 4");
        copyOnWriteArrayList.add("Item 5");
        copyOnWriteArrayList.add("Item 6");
        for (String item : copyOnWriteArrayList) {
           // System.out.println(item);
            if (item.equals("Item 5")) {
                copyOnWriteArrayList.add("Item 7");
            }
        }
        //System.out.println(copyOnWriteArrayList);

        Map<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        Thread t1 = new Thread(() -> iterateConcurrentHashMap(map));
        Thread t2 = new Thread(() -> mapReader(map));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public void mapReader(Map<Integer, String> map) {
//        try {
//            Thread.sleep(1000);
//        }catch (InterruptedException e){}
        System.out.println("Reading Map: "+map);
    }

    public void iterateConcurrentHashMap(Map<Integer, String> map) {
        System.out.println("Iteration call started...... ");
        Iterator<Map.Entry<Integer, String>> mapItr = map.entrySet().iterator();

        while(mapItr.hasNext()) {
            Map.Entry<Integer, String> entry=mapItr.next();
            System.out.println(entry.getKey()+" --> "+entry.getValue());
            if(entry.getValue().equals("one")) {
                map.put(1, "four");
            }
        }
        System.out.println("Iteration call ended...... : "+map);
    }
}
