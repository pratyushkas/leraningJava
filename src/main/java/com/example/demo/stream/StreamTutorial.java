package com.example.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTutorial {

    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "Hello World",
                "We are Learning Java",
                "Stream is powerfull"
        );

        System.out.println(list
                .stream()
                .flatMap(sent->Stream.of(sent.split(" ")))
                .toList());
        List<String> list2 = Arrays.asList("Dany", "Alice","Harry","Jimmyshor");
        String collected = list2.stream().map(str -> str.toUpperCase()).collect(Collectors.joining(":"));
        System.out.println(collected);

        System.out.println(list2.stream().collect(Collectors.groupingBy(String::length)));
        System.out.println(list2.stream().collect(Collectors.groupingBy(String::length,Collectors.counting())));
        System.out.println(list2.stream().collect(Collectors.groupingBy(String::length,Collectors.joining(","))));
        System.out.println(list2.stream().collect(Collectors.groupingBy(String::length)));

        //Creating stream
        Stream.iterate(1,n->n+1).limit(10).forEach(System.out::println);
        Stream<Double> limit = Stream.generate(Math::random).limit(10);
        IntStream.rangeClosed(1,10).forEach(System.out::println);
        IntStream.range(1,10).forEach(System.out::println);
    }

    public static int factorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result*=i;
        }
        return result;
    }
}
