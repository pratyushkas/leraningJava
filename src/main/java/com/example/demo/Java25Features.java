package com.example.demo;

//Module Import Declarations :
//This feature adds a new way to import all packages that a module exports by using import module <modulename>;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
//Compact Source file.
//Now in Java 25, you can write a standalone Java source file without explicitly declaring a class — no class Demo { … }
// needed. The compiler automatically wraps your methods and variables inside a hidden “unnamed” class behind the scenes.
// This allows you to focus on learning programming basics without worrying about advanced concepts
// Make sure to have a main method, as it's the program's starting point.
@Slf4j
public class Java25Features {

    //Scoped values are a new feature in Java 25 that allow you to define variables that are scoped to a specific
    // block of code, such as a method or a lambda expression. Scoped values are similar to thread-local variables,
    // but they are more flexible and can be used in a wider range of scenarios.
    private static final ScopedValue<String> requestId = ScopedValue.newInstance();

    //Instance main method
    void main(){
        //System.out.println("Scoped value is: "+requestId.get());
        handleRequest(UUID.randomUUID().toString());
    }

    public static void handleRequest(String reqId){
        ScopedValue.where(requestId, reqId).run(()->{
            log.info("Starting process..");
            authenticate();
            fetchingData();
            log.info("Finished process..");
        });
    }
    public static void authenticate(){
        log.info("Authenticating request with id: "+requestId.get());
    }
    public static void fetchingData(){
        log.info("Fetching data for request with id: "+requestId.get());
    }

    //IO Helper Class: The IO helper class in Java 25 provides built-in methods for console input and output.
    //Key methods include:
    //IO.println(Object o) → prints the object followed by a newline.
    //IO.print(Object o) → prints without a newline.
    //IO.readln() → reads a line of text from the console and returns it as a String.
    //IO.readInt() → reads an integer input from the user.
    //IO.readDouble() → reads a double input from the user.
    public static void log(String message){
        IO.println("["+requestId.get()+"] "+message);
    }


    Employee emp1 = new Employee("John Doe", 30, "Engineering");
}
class Person {
    String name;
    int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Employee extends Person {
    String department;

    //Flexible Constructor Bodies
    public Employee(String name, int age, String department) {
        if(age<=0){
            throw new IllegalArgumentException("Age must be positive");
        }
        System.out.println("Initializing Employee...."+name);
        super(name, age);
        this.department = department;
        System.out.println("Initialized Employee...."+name+" assigned to department: "+department);
    }
}
