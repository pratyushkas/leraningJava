package com.example.demo.reflection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Person {
    private final String name;
    public int age;

    private void empStatus(){
        System.out.println("Employee is working");
    }
}
