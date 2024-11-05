package com.example;

public interface Dog {
    default void bark() {
        System.out.println("The dog barks.");
    }

    default void pet() {
        System.out.println("I pet the dog.");
    }
}


