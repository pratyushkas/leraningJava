package com.example.demo;

import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public final class Immutability {
    private final int orderId;
    private final List<Item> items;

    public Immutability(int orderId, List<Item> items) {
        this.orderId = orderId;
        this.items = List.copyOf(items); //or Collections.unmodifiableList(items);
    }
    @Override
    public String toString() {
        return orderId + " " + items.stream().map(Item::toString).collect(Collectors.joining(","));
    }
}

@Value
class Item{
     String name;
     int price;

}
