package com.example.demo.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionJava {

    void main() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person("John", 30);
        Method[] declaredMethods = person.getClass().getDeclaredMethods();
        Arrays.stream(declaredMethods).filter(method->method.getName().equals("empStatus"))
                .forEach(method ->{
                    try {
                        method.setAccessible(true);
                        method.invoke(person);
                    }catch(IllegalAccessException | InvocationTargetException ex){
                        System.out.println(ex.getMessage());
                    }
                });
        System.out.println(person.getName());
        Field personName = person.getClass().getDeclaredField("name");
        personName.setAccessible(true);
        personName.set(person, "Doe");
        System.out.println(person.getName());
    }
}
