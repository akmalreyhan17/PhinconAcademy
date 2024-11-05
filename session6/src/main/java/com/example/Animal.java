package com.example;

public interface Animal {
    default void eat() {
        System.out.println("This animal eats food.");
    }
}


