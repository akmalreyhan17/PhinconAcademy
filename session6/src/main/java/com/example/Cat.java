package com.example;

public interface Cat {
    default void meow() {
        System.out.println("The cat meows.");
    }

    default void pet() {
        System.out.println("I pet the cat.");
    }
}


