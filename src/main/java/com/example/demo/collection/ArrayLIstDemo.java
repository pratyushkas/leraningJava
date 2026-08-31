package com.example.demo.collection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayLIstDemo {
    void main() throws NoSuchFieldException, IllegalAccessException {
        List<String> listDemo = new ArrayList<>();
        for(int i = 0; i < 11; i++) {
            listDemo.add("Item " + i);
        }
        Field defaultCapacity = listDemo.getClass().getDeclaredField("elementData");
        defaultCapacity.setAccessible(true);
        Object[] internalArray = (Object[])defaultCapacity.get(listDemo);
        System.out.println("Element data length: " + internalArray.length);;
        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(8);
        list.add(2);
        list.remove(Integer.valueOf(2));
        System.out.println("Element data: " + list);;
    }

}
