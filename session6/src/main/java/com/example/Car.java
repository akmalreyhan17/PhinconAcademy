package com.example;

public class Car {
    String color;

    Car(String color) {
        this.color = color; 
        // 'this.color' refers to the field, 
        // 'color' refers to the parameter
    }

    void displayColor() {
        System.out.println("The car color is " + this.color);
    }

    void drive() {
        System.out.println("The car drives");
    }
}



