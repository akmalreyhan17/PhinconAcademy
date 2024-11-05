package com.example;

public class Programmer extends Employee implements Dog{
    @Override
    void work() {
        System.out.println("Programming new code.");
    }

    void work(String code) {
        System.out.println("Programming " + code);
    }

    void defaultWork() {
        super.work(); // Print "The employee is working."
    }
}


