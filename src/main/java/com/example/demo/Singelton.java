package com.example.demo;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;

public class Singelton implements Serializable,Cloneable {
    private static Singelton instance;

    private Singelton() {
        if(instance!= null){
            throw new RuntimeException("Singelton already initialized");
        }
    }

    public static Singelton getInstance(){
        if(instance== null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Singelton.class){
                if(instance==null){
                    instance = new Singelton();
                }
            }

        }
        return instance;
    }

    @Serial
    private Object readResolve(){
        return getInstance();
    }

    @Override
    public Singelton clone() throws CloneNotSupportedException {
        return (Singelton)super.clone();
    }
}
