package com.example.demo;

@FunctionalInterface
interface FunctionalInterfac {
    int mathOPeration(int a, int b);
}

public class FunctionalInterfaceDemo{
    void main(){
        FunctionalInterfac addition = (a, b) -> a + b;
        FunctionalInterfac subtraction = (a, b) -> a - b;
        FunctionalInterfac multiplication = (a, b) -> a * b;
        System.out.println(addition.mathOPeration(2,3));
    }
}

