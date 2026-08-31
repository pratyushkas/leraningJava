package com.example.demo.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TopFiveJavaPgm {

    public static void main(String[] args) throws InterruptedException {
        //Find the first non-repeated character in a String
        String str = "swiss";
        LinkedHashMap<Character, Long> collect = str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new,
                Collectors.counting()));
        collect.entrySet().stream().filter(entry->entry.getValue()==1)
                .map(Map.Entry::getKey).findFirst().ifPresent(System.out::println);

        //Find the second-highest number in an array/List
        List<Integer> arr = Arrays.asList(10, 5, 20, 8, 20, 15);
        System.out.println(arr.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get());

        //Find duplicate elements in a List
        List<Integer> list = Arrays.asList(1, 3, 2, 2, 4, 3, 5);
        list.stream().distinct().filter(item -> Collections.frequency(list, item) > 1).forEach(System.out::println);

        Thread t1 = new Thread(()-> System.out.println(Thread.currentThread().getName()+" is running"));
        t1.start();
        Thread.startVirtualThread(()-> System.out.println(Thread.currentThread().getName()+" is running")).join();

        List<String> list1 = Arrays.asList("apple", "banana", "cherry","dose","date", "elderberry");
        list1.sort(Comparator.comparing(String::length).reversed().thenComparing(Comparator.naturalOrder()));
        System.out.println(list1);

    }
}
