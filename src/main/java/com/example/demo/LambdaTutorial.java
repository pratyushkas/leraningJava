package com.example.demo;

public class LambdaTutorial {
    public static void main(String[] args) {
        MathOperations addOperation = (a,b)->a+b;
        MathOperations subOperation = (a,b)->a-b;
        System.out.println(addOperation.operate(5,2));
        System.out.println(subOperation.operate(5,2));
    }
}

interface MathOperations{
    int operate(int a, int b);
}


